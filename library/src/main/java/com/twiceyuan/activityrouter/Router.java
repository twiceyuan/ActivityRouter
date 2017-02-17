package com.twiceyuan.activityrouter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by twiceYuan on 15/02/2017.
 * <p>
 * Activity Router
 */
public class Router {

    private static Map<String, Class<? extends Activity>> mActivityMap = new ConcurrentHashMap<>();

    public static void init(Context context) {
        List<Class<? extends Activity>> activities = getAllActivities(context);
        for (Class<? extends Activity> activityClass : activities) {
            String routeName = getRouteName(activityClass);
            if (!TextUtils.isEmpty(routeName)) {
                mActivityMap.put(routeName, activityClass);
            }
        }
    }

    public static Class<? extends Activity> path(String path) {
        Class<? extends Activity> activityClass = mActivityMap.get(path);
        if (activityClass == null) {
            throw new IllegalStateException("Not activity found by path '" + path + "'");
        }
        return activityClass;
    }

    public static Class<?extends Activity> deepPath(Context context, String path) {
        openPrevious(context, path);
        return path(path);
    }

    public static void startPath(Context context, String pathString) {
        context.startActivity(new Intent(context, path(pathString)));
    }

    public static void startDeepPath(Context context, String deepPath) {
        context.startActivity(new Intent(context, deepPath(context, deepPath)));
    }

    private static void openPrevious(Context context, String path) {
        String[] paths = path.split("/");
        if (paths.length > 2) {
            StringBuilder previousPath = new StringBuilder("/");
            for (int i = 1; i < paths.length - 1; i++) {
                previousPath.append(paths[i]);
                Class<? extends Activity> levelClass = mActivityMap.get(previousPath.toString());
                if (levelClass != null) {
                    Intent intent = new Intent(context, levelClass);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        }
    }

    public static void dispatchUriIntent(Context context, Intent intent) {
        if (intent == null || intent.getData() == null) return;
        String path = intent.getData().getPath();
        openPrevious(context, path);
        Class<? extends Activity> activityClass = mActivityMap.get(path);
        if (activityClass == null) return;
        intent.setClass(context, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取注解的路径名
     *
     * @param activityClass 注解的 activity 类
     * @return 该类注解定义的类名
     */
    private static String getRouteName(Class<? extends Activity> activityClass) {
        Annotation[] declaredAnnotations = activityClass.getDeclaredAnnotations();
        if (declaredAnnotations == null || declaredAnnotations.length == 0) {
            return null;
        }
        for (Annotation annotation : declaredAnnotations) {
            if (annotation instanceof Route) {
                return ((Route) annotation).value();
            }
        }
        return null;
    }

    private static List<Class<? extends Activity>> getAllActivities(Context context) {
        PackageInfo info = context.getPackageManager().getPackageArchiveInfo(
                context.getPackageCodePath(),
                PackageManager.GET_ACTIVITIES);

        ActivityInfo[] activities = info.activities;
        List<Class<? extends Activity>> activityClasses = new ArrayList<>();
        for (ActivityInfo activity : activities) {
            try {
                //noinspection unchecked
                activityClasses.add((Class<? extends Activity>) Class.forName(activity.name));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return activityClasses;
    }
}

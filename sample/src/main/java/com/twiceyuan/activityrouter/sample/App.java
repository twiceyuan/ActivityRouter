package com.twiceyuan.activityrouter.sample;

import android.app.Application;

import com.twiceyuan.activityrouter.Router;

/**
 * Created by twiceYuan on 15/02/2017.
 *
 * Application instance
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.init(this);
    }
}

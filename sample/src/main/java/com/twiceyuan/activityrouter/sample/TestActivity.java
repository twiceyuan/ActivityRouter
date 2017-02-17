package com.twiceyuan.activityrouter.sample;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.twiceyuan.activityrouter.Route;

/**
 * Created by twiceYuan on 15/02/2017.
 * <p>
 * 测试
 */
@Route("/test")
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView textView = (TextView) findViewById(R.id.tv_test);
        Uri data = getIntent().getData();
        if (data != null) {
            String helloString = data.getQueryParameter("hello_string");
            if (!TextUtils.isEmpty(helloString)) {
                textView.setText(String.format("取到参数：%s", helloString));
            }
        }
    }
}

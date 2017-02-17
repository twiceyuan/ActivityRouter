package com.twiceyuan.activityrouter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.twiceyuan.activityrouter.Route;

/**
 * Created by twiceYuan on 17/02/2017.
 *
 * 深层 Activity
 */
@Route("/test/deep")
public class DeepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep);
    }
}

package com.twiceyuan.activityrouter.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.twiceyuan.activityrouter.Router;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Router.dispatchUriIntent(this, getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Router.dispatchUriIntent(this, intent);
    }

    public void test(View view) {
        startActivity(new Intent(this, Router.path("/test")));
    }

    public void deep(View view) {
        startActivity(new Intent(this, Router.deepPath(this, "/test/deep")));
    }
}

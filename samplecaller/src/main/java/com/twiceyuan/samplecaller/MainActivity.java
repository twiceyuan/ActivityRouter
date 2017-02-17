package com.twiceyuan.samplecaller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callHome(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("router:///"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void callTest(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("router:///test"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void callTestWithParams(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("router:///test?hello_string=HelloWorld!"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void callDeep(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("router:///test/deep"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

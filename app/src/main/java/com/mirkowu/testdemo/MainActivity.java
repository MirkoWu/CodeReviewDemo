package com.mirkowu.testdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;

import com.mirkowu.testdemo.webviewAIDL.WebViewActivity;

public class MainActivity extends AppCompatActivity {

    public static String value = "2222222";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void jumpLeak(View view) {
        LeakActivity.start(this);
    }

    public void jumpWebView(View view) {
        WebViewActivity.start(this,"www.baidu.com");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("value========== " + value);
    }

    public void killProcess(View view) {
        ActivityManager manager=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
        manager.killBackgroundProcesses("com.mirkowu.testdemo:webview");
    }
}
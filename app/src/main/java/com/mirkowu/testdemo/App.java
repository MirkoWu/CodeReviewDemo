package com.mirkowu.testdemo;

import android.app.Application;

import com.mirkowu.testdemo.leakcancry.LeakCanary;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.getInstance().install(this);
        System.out.println("-------------onCreate -----------------------");
    }
}

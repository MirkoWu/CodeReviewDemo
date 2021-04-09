package com.mirkowu.testdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class LeakActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, LeakActivity.class);
//    starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = LeakActivity.this.getIntent();
                    if (intent != null) {
                        Bundle b = intent.getExtras();
                    }
                    Thread.sleep(5000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("LeakActivity", "执行完");
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy---");
    }
}
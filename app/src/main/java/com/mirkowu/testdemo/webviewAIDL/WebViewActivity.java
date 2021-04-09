package com.mirkowu.testdemo.webviewAIDL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.mirkowu.testdemo.MainActivity;
import com.mirkowu.testdemo.R;
import com.mirkowu.testdemo.webview.IWebViewAidlCallback;
import com.mirkowu.testdemo.webview.IWebViewAidlInterface;

public class WebViewActivity extends AppCompatActivity {

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra("url", url);
        context.startActivity(starter);
    }

    private ServiceConnection connection;
    IWebViewAidlInterface binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // IWebViewAidlCallback.Stub.asInterface(service);
                binder = IWebViewAidlInterface.Stub.asInterface(service);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(new Intent(this, MainRemoteService.class), connection, Context.BIND_AUTO_CREATE);
    }

    public void refresh(View view) {
        MainActivity.value = "33333333333333";

        System.out.println("value========== " + MainActivity.value);
        String url = getIntent().getStringExtra("url");
        System.out.println("url = " + url);
        if (binder != null) {
            try {

                binder.handleWebAction(1, "refresh", "hahahhah", new IWebViewAidlCallback.Stub() {
                    @Override
                    public void onResult(int responseCode, String actionName, String response) throws RemoteException {
                        int pid = android.os.Process.myPid();
                        System.out.println("Web 进程=(" + pid + ") 返回 responseCode = " + responseCode + ", actionName = " + actionName + ", response = " + response);
                    }


                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            unbindService(connection);
        }
    }
}
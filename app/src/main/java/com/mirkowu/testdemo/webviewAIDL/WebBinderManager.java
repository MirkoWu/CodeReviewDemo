package com.mirkowu.testdemo.webviewAIDL;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.mirkowu.testdemo.MainActivity;
import com.mirkowu.testdemo.webview.IWebViewAidlCallback;
import com.mirkowu.testdemo.webview.IWebViewAidlInterface;

public class WebBinderManager {
    private static volatile WebBinderManager INSTANCE;

    private WebBinderManager() {

    }

    public static WebBinderManager getInstance() {
        if (INSTANCE == null) {
            synchronized (WebBinderManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WebBinderManager();
                }
            }
        }
        return INSTANCE;
    }

    private ServiceConnection connection;
    private IWebViewAidlInterface binder;

    private synchronized void connectBinderService(Context context) {
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
        context.bindService(new Intent(context, MainRemoteService.class), connection, Context.BIND_AUTO_CREATE);
    }

    public synchronized void unbindService(Context context) {
        if (connection != null) {
            context.unbindService(connection);
        }
    }

    public void refresh(View view) {
        MainActivity.value = "33333333333333";

        System.out.println("value========== " + MainActivity.value);

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
}

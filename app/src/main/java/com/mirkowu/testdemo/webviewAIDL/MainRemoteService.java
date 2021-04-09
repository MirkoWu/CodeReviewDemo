package com.mirkowu.testdemo.webviewAIDL;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;

import com.mirkowu.testdemo.webview.IWebViewAidlCallback;
import com.mirkowu.testdemo.webview.IWebViewAidlInterface;

public class MainRemoteService extends Service {
    public MainRemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private IWebViewAidlInterface.Stub binder = new IWebViewAidlInterface.Stub() {
        @Override
        public void handleWebAction(int level, String actionName, String jsonParams, final IWebViewAidlCallback callback) throws RemoteException {
            int pid = android.os.Process.myPid();
            System.out.println("APP进程=(" + pid + ") 调用 level = " + level + ", actionName = " + actionName + ", jsonParams = " + jsonParams + ", callback = " + callback);
            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (callback != null) {
                            callback.onResult(1, "test", "{key:\"value\"}");
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    };

}

// IWebViewAidlInterface.aidl
package com.mirkowu.testdemo.webview;
import com.mirkowu.testdemo.webview.IWebViewAidlCallback;
// Declare any non-default types here with import statements

interface IWebViewAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleWebAction( int level, String actionName, String jsonParams,in IWebViewAidlCallback callback);
}

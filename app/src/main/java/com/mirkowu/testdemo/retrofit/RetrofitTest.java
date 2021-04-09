package com.mirkowu.testdemo.retrofit;

import android.os.Looper;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RetrofitTest {
    public static void main(String[] args) {

//        OkHttpClient client = new OkHttpClient.Builder().build();
//        Request request = new Request.Builder().url("http://www.baidu.com/").get().build();
//
//        Call call = client.newCall(request);
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("call = " + call + ", e = " + e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("call = " + call + ", response = " + response.body().string());
//            }
//        });
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        try {
//
//            Response response = call.clone().execute();
//            System.out.println("response = " + response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        InterfaceDynamicProxy interfaceDynamicProxy = new InterfaceDynamicProxy();
        APIService service = (APIService) interfaceDynamicProxy.newProxy(APIService.class);
        System.out.println("Obj = " + service);

        Call call = service.getApi("我的");
        System.out.println("call = " + call.request());

        System.out.println();
        System.out.println();
        System.out.println();

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("call = " + call + ", e = " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("call = " + call + ", response = " + response.body().string());
            }
        });

    }
}

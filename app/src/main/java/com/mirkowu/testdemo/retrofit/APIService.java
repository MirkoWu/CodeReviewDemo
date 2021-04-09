package com.mirkowu.testdemo.retrofit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public interface APIService {

    @GET("http://www.baidu.com/s")
    Call getApi(@Query("wd") String wd/*, @Query("value") String value*/);
}

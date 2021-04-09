package com.mirkowu.testdemo.retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ServiceMethod {
    private Method method;
    private Object[] args;

    private String httpMethod;
    private String url;
    private HttpUrl httpUr;
    private HttpUrl.Builder urlBuilder;
    RequestBody body;

    public ServiceMethod(Method method, Object[] args) {
        this.method = method;
        this.args = args;

    }

    public Request toRequest() {
//        OkHttpClient client = new OkHttpClient.Builder().build();

        Annotation[] annotations = method.getAnnotations();
        Type[] types = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class returnType = method.getReturnType();


        for (Annotation annotation : annotations) {
            parseAnnotation(annotation);
        }

        for (Type type : types) {

        }

        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            for (Annotation annotation : parameterAnnotation) {
                parseParameterAnnotation(annotation, i);
            }
        }

        Request.Builder builder = new Request.Builder();
        builder.url(urlBuilder.build());
        builder.method(httpMethod, body);


        return builder.build();
    }

    private void parseParameterAnnotation(Annotation annotation, int i) {

        if (annotation instanceof Query) {
            String key = ((Query) annotation).value();
            urlBuilder.addQueryParameter(key, args[i].toString());
            body = null;
        } else if (annotation instanceof Form) {
            String key = ((Form) annotation).value();
            urlBuilder.addQueryParameter(key, args[i].toString());
            body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        } else {
            throw new RuntimeException("不支持该方法");
        }
    }

    private void parseAnnotation(Annotation annotation) {
        if (httpMethod != null) {
            throw new RuntimeException(String.format("已设置请求方法 %s %s", httpMethod, url));
        }


        if (annotation instanceof GET) {
            httpMethod = HttpMethod.GET;
            url = ((GET) annotation).value();
            httpUr = HttpUrl.parse(url);
            if (urlBuilder == null) {
                urlBuilder = httpUr.newBuilder();
            }
        } else if (annotation instanceof POST) {
            httpMethod = HttpMethod.POST;
            url = ((POST) annotation).value();
            httpUr = HttpUrl.parse(url);

            if (urlBuilder == null) {
                urlBuilder = httpUr.newBuilder();
            }
        } else {
            throw new RuntimeException("不支持该方法");
        }
    }

}

package com.mirkowu.testdemo.retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class InterfaceDynamicProxy<T> implements InvocationHandler {

    private Class service;
    private OkHttpClient okHttpClient;

    public <T> T newProxy(Class<T> service) {
        this.service = service;
//        return (T) Proxy.newProxyInstance(service.getClassLoader(), service.getInterfaces(), this);
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(/*"proxy = " + proxy +*/ ", method = " + method.getName() + ", args = " + Arrays.toString(args));

        //过滤掉
        if (method.getDeclaringClass()==Object.class) {
            return method.invoke(this,args);
        }


        Annotation[] annotations = method.getAnnotations();
        Type[] types = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class returnType = method.getReturnType();

        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            System.out.println("parameterAnnotations = annotation" + Arrays.toString(parameterAnnotation));
//            for (Annotation annotation: parameterAnnotation) {
//                System.out.println("parameterAnnotations = annotation" + Arrays.toString(parameterAnnotation)  );
//            }
        }
        System.out.println("annotations = " + Arrays.toString(annotations)
                + ", types = " + Arrays.toString(types)
                + ", parameterAnnotations = " + Arrays.toString(parameterAnnotations)
                + ", returnType = " + returnType
        );


//        Object obj = method.invoke(service, args);
//        System.out.println("obj invoke = " + obj);

        ServiceMethod serviceMethod = new ServiceMethod(method, args);



       Request request= serviceMethod.toRequest();
        if (okHttpClient==null) {
            okHttpClient=new OkHttpClient();
        }
       Call call= okHttpClient.newCall(request);
        return call;
//        return obj;

//        return getCall(method, args);
    }


}

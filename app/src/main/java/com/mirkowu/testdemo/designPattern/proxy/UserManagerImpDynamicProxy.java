package com.mirkowu.testdemo.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserManagerImpDynamicProxy implements InvocationHandler {

    private Object object;

    public Object newProxy(Object object) {
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置内容----- ");
        Object obj = method.invoke(object, args);
        System.out.println("后置内容----- ");

        return obj;
    }
}

package com.mirkowu.testdemo.designPattern.proxy;


public class ProxyTest {
    public static void main(String[] args) {
        //代理本身 用通过中间人 来进行执行 类似中介
        //动态代理 解决一类问题
        UserManagerImpDynamicProxy proxy=new UserManagerImpDynamicProxy();
        IUserManager userManager= (IUserManager) proxy.newProxy(new UserManagerImpl());
        userManager.addUser("");
        userManager.delUser("");

        //静态代理
        UserManagerImpStaticProxy staticProxy=new UserManagerImpStaticProxy(new UserManagerImpl());
        staticProxy.addUser("");
        staticProxy.delUser("");


    }
}

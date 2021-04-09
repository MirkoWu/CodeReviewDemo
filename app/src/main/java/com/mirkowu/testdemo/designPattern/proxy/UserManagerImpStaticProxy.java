package com.mirkowu.testdemo.designPattern.proxy;

public class UserManagerImpStaticProxy implements IUserManager {

    UserManagerImpl userManager;

    public UserManagerImpStaticProxy(UserManagerImpl userManager ){
        this.userManager=userManager;
    }


    @Override
    public void addUser(String str) {
        System.out.println("addUser 前 str = " + str );
        userManager.addUser(str);
        System.out.println("addUser 后 str = " + str );
    }

    @Override
    public void delUser(String str) {
        System.out.println("delUser 前 str = " + str );
        userManager.delUser(str);
        System.out.println("delUser 后 str = " + str );
    }
}

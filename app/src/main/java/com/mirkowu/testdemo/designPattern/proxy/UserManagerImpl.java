package com.mirkowu.testdemo.designPattern.proxy;

public class UserManagerImpl implements IUserManager {
    @Override
    public void addUser(String str) {
        System.out.println("addUser str = " + str);
    }

    @Override
    public void delUser(String str) {
        System.out.println("delUserstr = " + str);
    }
}

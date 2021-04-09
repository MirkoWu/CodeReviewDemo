package com.mirkowu.testdemo.designPattern.facade;

public class FacadeTest {
    public static void main(String[] args) {
        IFacade facade=new Facade();
        facade.start();
        facade.release();
    }
}

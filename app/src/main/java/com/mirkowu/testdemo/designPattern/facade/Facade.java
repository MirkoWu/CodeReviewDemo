package com.mirkowu.testdemo.designPattern.facade;

import java.util.ArrayList;

public class Facade implements IFacade {
    FacadeSystem facadeSystem1 = new FacadeSystem();
    FacadeSystem facadeSystem2 = new FacadeSystem();

    @Override
    public void start() {
        facadeSystem1.doSomething();
        facadeSystem2.doSomething();
    }

    @Override
    public void release() {
        facadeSystem1.doSomething();
        facadeSystem2.doSomething();
    }
}

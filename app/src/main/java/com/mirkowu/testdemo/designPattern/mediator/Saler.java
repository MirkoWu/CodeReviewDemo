package com.mirkowu.testdemo.designPattern.mediator;

public class Saler {
    Mediator mediator;

    public Saler(Mediator mediator) {
        this.mediator = mediator;
    }

    public void submit(){
        System.out.println("房主 提交房源");
        mediator.submit();
    }
}

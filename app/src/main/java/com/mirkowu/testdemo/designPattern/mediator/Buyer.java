package com.mirkowu.testdemo.designPattern.mediator;

public class Buyer {
    Mediator mediator;

    public Buyer(Mediator mediator) {
        this.mediator = mediator;
    }

    public void qurey(){
        System.out.println("买方查询");
        mediator.qurey();
    }
}

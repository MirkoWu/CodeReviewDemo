package com.mirkowu.testdemo.designPattern.mediator;

/**
 * 中介者模式
 */
public class MediatorTest {
    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        Buyer buyer = new Buyer(mediator);
        Saler saler = new Saler(mediator);
        buyer.qurey();

        saler.submit();

        buyer.qurey();
    }

}

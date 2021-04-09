package com.mirkowu.testdemo.designPattern.mediator;

public class Mediator {
    Buyer buyer;
    Saler saler;
    public int houseNum;

//    public void action(){
//        if(saler!=null) saler.submit();
//        if(saler!=null)  buyer.qurey();
//    }

    public void submit() {

        houseNum++;
        System.out.println("获取房源 共 " + houseNum);
    }

    public void qurey() {
        System.out.println("剩余房源" + houseNum);
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Saler getSaler() {
        return saler;
    }

    public void setSaler(Saler saler) {
        this.saler = saler;
    }
}

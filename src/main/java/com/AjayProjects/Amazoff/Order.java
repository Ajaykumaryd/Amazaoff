package com.AjayProjects.Amazoff;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id,String deliveryTime){
        this.id =id;
        this.deliveryTime = TimeUtil.convertTime(deliveryTime);
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }


    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = TimeUtil.convertTime(deliveryTime);
    }


    public int getDeliveryTime() {
        return deliveryTime;
    }
}

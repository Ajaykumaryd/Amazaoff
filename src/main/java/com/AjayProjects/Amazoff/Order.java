package com.AjayProjects.Amazoff;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id,String deliveryTime){
        this.id =id;
        this.deliveryTime = convertDeliveryTime(deliveryTime);
    }

    private int convertDeliveryTime(String deliveryTime){
    String[] time=deliveryTime.split(":");
    return Integer.parseInt(time[0])*60+Integer.parseInt(time[1]);
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String convertDeliveryRime(int deliveryTime){
        int hh=deliveryTime/60;
        int mm=deliveryTime%60;
        String HH=String.valueOf(hh);
        String MM=String.valueOf(mm);
        return String.format("%s:%s",HH,MM);
    }

    public String getDeliveryTimeAsString(){
      return convertDeliveryRime(this.deliveryTime);
    }

    public void setDeliveryTime(int deliveryTime) {

        this.deliveryTime = deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {

        this.deliveryTime = convertDeliveryTime(deliveryTime);
    }


}

package com.AjayProjects.Amazoff;

public class TimeUtil {

    private TimeUtil() {
    }
     public static String convertTime(int deliveryTime){
        //565 in int -> 09:25
        int hh=deliveryTime/60;
        int mm=deliveryTime%60;
        String HH=String.valueOf(hh);
        String MM=String.valueOf(mm);
        return String.format("%s:%s",HH,MM);
    }

    public static int convertTime(String deliveryTime) {
        String[] time = deliveryTime.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }




}

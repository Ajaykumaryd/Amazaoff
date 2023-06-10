package com.AjayProjects.Amazoff;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Service
public  class OrderRepository {

    static  HashMap<String,Order> orderMap=new HashMap<>();
    static HashMap<String,DeliveryPartner> partnerMap=new HashMap<>();
    static HashMap<String,ArrayList<String>> partnerOrderMap=new HashMap<>(); //key partner id value is order ids

    static HashMap<String,String> orderPartnerMap=new HashMap<>();



    public static void addOrder(Order order) {
        orderMap.put(order.getId(),order);
    }

    public static void addPartner(DeliveryPartner partner) {
        partnerMap.put(partner.getId(),partner);
    }

    public static Optional<Order> getOrder(String orderId){
     if(orderMap.containsKey(orderId)){
        return Optional.of(orderMap.get(orderId));
     }else {
         return Optional.empty();
     }
    }

    public static Optional<DeliveryPartner> getPartner(String partnerId){
     if(partnerMap.containsKey(partnerId)){
         return Optional.of(partnerMap.get(partnerId));
     }else {
         return Optional.empty();
     }
    }

    public static void addOrderPartnerPair(String orderId, String partnerId){
        orderPartnerMap.put(orderId,partnerId);
        ArrayList<String> orders=partnerOrderMap.getOrDefault(partnerId,new ArrayList<>());  //if order is already there
        orders.add(orderId);
        partnerOrderMap.put(partnerId,orders);
    }

    public static Order getOrderbyId(String Id) {
        return orderMap.get(Id);
    }



    public int getOrders(String id){
    ArrayList<String> ans= partnerOrderMap.get(id);
    return ans.size();
    }

    public static ArrayList<String> getAssigned() {
    return new ArrayList<>(orderPartnerMap.keySet());
    }

    public static ArrayList<String> GetListAllOrders() {
     return new ArrayList<>(orderMap.keySet());
    }


}






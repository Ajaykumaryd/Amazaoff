package com.AjayProjects.Amazoff;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Service
public  class OrderRepository {

    HashMap<String,Order> orderMap=new HashMap<>();
    HashMap<String,DeliveryPartner> partnerMap=new HashMap<>();
    HashMap<String,ArrayList<String>> orderPartnerMap=new HashMap<>(); //key partner id value is order ids



    public void addOrder(Order order) {
        orderMap.put(order.getId(),order);
    }

    public void addPartner(DeliveryPartner partner) {
        partnerMap.put(partner.getId(),partner);
    }

    public Optional<Order> getOrder(String orderId){
     if(orderMap.containsKey(orderId)){
        return Optional.of(orderMap.get(orderId));
     }else {
         return Optional.empty();
     }
    }

    public Optional<DeliveryPartner> getPartner(String partnerId){
     if(partnerMap.containsKey(partnerId)){
         return Optional.of(partnerMap.get(partnerId));
     }else {
         return Optional.empty();
     }
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
        ArrayList<String> orders=orderPartnerMap.getOrDefault(partnerId,new ArrayList<>());  //if order is already there
        orders.add(orderId);
        orderPartnerMap.put(partnerId,orders);
    }

    public Order getOrderbyId(String Id) {
    return orderMap.get(Id);
    }

    public int getOrders(String id){
    ArrayList<String> ans= orderPartnerMap.get(id);
    return ans.size();
    }


    public ArrayList<String> getList(String id) {
     ArrayList<String> list=orderPartnerMap.get(id);
     return list;
    }




}

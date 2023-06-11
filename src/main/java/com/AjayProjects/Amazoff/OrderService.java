package com.AjayProjects.Amazoff;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
@Log4j2
public class OrderService {


    @Autowired
    static
    OrderRepository repository;




    public static void addOrder(Order order) {
        repository.addOrder(order);
    }

    public static void addPartner(String id) {
        DeliveryPartner partner=new DeliveryPartner(id);
     repository.addPartner(partner);
    }

    public static void addPair(String orderId,String partnerId) {
        Optional<Order> optionalOrder =repository.getOrder(orderId);
        Optional<DeliveryPartner> optionalDeliveryPartner=repository.getPartner(partnerId);
        if(optionalOrder.isEmpty()) {
            log.warn("order ID is not present" + orderId);
            throw new RuntimeException("order ID is not present " + orderId);
        }
        if(optionalDeliveryPartner.isEmpty()) {
            log.warn("Partner ID is not present" + partnerId);
            throw new RuntimeException("Partner ID is not present " + partnerId);
        }
        DeliveryPartner partner=optionalDeliveryPartner.get();
        partner.setNumberOfOrders(partner.getNumberOfOrders()+1);
        repository.addPartner(partner);
        repository.addOrderPartnerPair(orderId,partnerId);
        }

    public static Order getOrder(String Id) throws RuntimeException {
    Optional<Order> optionalOrder=repository.getOrder(Id);
    if(optionalOrder.isPresent()){
       return optionalOrder.get();
    }
        throw new RuntimeException("Order Id is Not found");
    }

    public static DeliveryPartner addPartnerId(String id) {
    Optional<DeliveryPartner> optionalDeliveryPartner=repository.getPartner(id);
    if(optionalDeliveryPartner.isPresent()){
        return optionalDeliveryPartner.get();
    }else{
        throw new RuntimeException("Partner Id is Not Present");
    }
    }
    public static int getOrders(String id) {

        Optional<DeliveryPartner> optionalDeliveryPartner=repository.getPartner(id);
        if(optionalDeliveryPartner.isPresent()){
         return optionalDeliveryPartner.get().getNumberOfOrders();
        }
        return 0;
//        Method2
//        int orders=repository.getOrders(id);
//     return orders;
    }


    public static ArrayList<String> getOrdersByPartnerId(String partnerId) {
    ArrayList<String> ans=repository.getAssigned();
    return ans;
    }


    public static ArrayList<String> getAllOrders() {
    ArrayList<String> ans=repository.GetListAllOrders();
    return ans;
    }

    public static Integer getUnassignedOrders() {
    return repository.GetListAllOrders().size()-OrderRepository.getAssigned().size();
    }


    public static Integer getOrdersLeftForPartnerAfterTime(String time,String partnerId) {
       ArrayList<String> orderIds = repository.getAllOrdersforPartner(partnerId);
       ArrayList<Order> orders=new ArrayList<>();
        for(String Id:orderIds){
              Order order=repository.getOrder(Id).get();
              if(order.getDeliveryTime()>TimeUtil.convertTime(time));
              orders.add(order);
            }
        return orders.size();
      }

        public String getLastDeliveryTimeForPartner(String partnerId) {
//            ArrayList<String> orderIds = repository.getAllOrderforPartner(partnerId);
            ArrayList<String> orderIds = repository.getAllOrdersforPartner(partnerId);
            int max = 0;
            for(String orderId: orderIds) {
                int deliveryTime = repository.getOrder(orderId).get().getDeliveryTime();
                if(deliveryTime>max){
                    max = deliveryTime;
                }
            }
            return TimeUtil.convertTime(max);
        }


         public static void deletePartner(String partnerId){
         repository.delete(partnerId);
    }
}









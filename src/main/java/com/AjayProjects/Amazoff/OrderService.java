package com.AjayProjects.Amazoff;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log4j2
public class OrderService {


    @Autowired
    OrderRepository repository;

    public void addOrder(Order order) {
        repository.addOrder(order);
    }

    public void addPartner(String id) {
        DeliveryPartner partner=new DeliveryPartner(id);
     repository.addPartner(partner);
    }

    public void addPair(String orderId,String partnerId) {

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

    public Order getOrder(String Id) throws RuntimeException {
    Optional<Order> optionalOrder=repository.getOrder(Id);
    if(optionalOrder.isPresent()){
       return optionalOrder.get();
    }
        throw new RuntimeException("Order Id is Not found");
    }
}


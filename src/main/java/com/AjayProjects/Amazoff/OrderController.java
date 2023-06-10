package com.AjayProjects.Amazoff;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
   OrderService service;

    @PostMapping("/add-order")
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        service.addOrder(order);
        return new ResponseEntity<>("New order added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-Partner/{id}")
    public ResponseEntity<String> addPartner(@PathVariable String id){
     service.addPartner(id);
     return new ResponseEntity<>("Partner added Sucessfully",HttpStatus.CREATED);
    }

    @PutMapping("/add-order-partner")
    public ResponseEntity<String> addPartnerOrder(@RequestParam String OrderId,@RequestParam String partnerId){

     try{
      service.addPair(OrderId,partnerId);
      return new ResponseEntity<>("New Order Pair added SucessFully",HttpStatus.CREATED);
     }catch (RuntimeException ex){
      return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
     }
    }
    @GetMapping("/get-order-by-id/{Id}")
    public ResponseEntity<Order> getOrderbyId(@PathVariable String Id) throws RuntimeException{
     try {
      Order order=service.getOrder(Id);
      return new ResponseEntity<>(order,HttpStatus.OK);
     }catch (RuntimeException e){
      System.out.println(" Not found Order with this ID");
       return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
     }
    }


    @GetMapping("get-partner-by-id/{Id}")
     public ResponseEntity<DeliveryPartner> getPartnerbyId(@PathVariable String Id){
     try {
      DeliveryPartner partner=service.addPartnerId(Id);
      return new ResponseEntity<>(partner,HttpStatus.CREATED);
     }catch (RuntimeException ex){
      return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
     }
    }

    @GetMapping("get-order-count-by-partner-id/{Id}")

    public ResponseEntity<Integer>numberOfOrders(@PathVariable String Id){
    int numberOrders=service.getOrders(Id);
    return new ResponseEntity<>(numberOrders,HttpStatus.OK);
    }

    @GetMapping("/get-orders-by-partner-id/{Id}")
    public ResponseEntity<ArrayList<String>> getListofOrders(@PathVariable String Id){
    ArrayList<String> ans=service.getList(Id);
    return new ResponseEntity<>(ans,HttpStatus.ACCEPTED);
    }






}

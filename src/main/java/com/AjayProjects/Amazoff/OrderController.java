package com.AjayProjects.Amazoff;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    service.addPair(OrderId,partnerId);
    return new ResponseEntity<>("New Order Pair added SucessFully",HttpStatus.CREATED);
    }


}

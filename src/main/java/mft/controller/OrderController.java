package mft.controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.*;
import mft.model.service.OrderDetailsService;
import mft.model.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
@Log4j
public class OrderController {
    private static OrderController controller=new OrderController();

    private OrderController() {
    }

    public static OrderController getController() {
        return controller;
    }

//    private static float getAmount (ArrayList<OrderDetails> items){
//        float amount=0;
//        for (OrderDetails orderDetails : items) {
//            amount+= orderDetails.getProducts().getPrice() * orderDetails.getQuantity();
//        }
//        return amount;
//    }
    public Orders save(Customer customer ,OrderDetails item, Payment payment, Double amount, float discount, LocalDateTime ordertime ) throws Exception {
        Orders orders =
                Orders
                        .builder()
                        .customer(Customer
                                .builder()
                                .id(customer.getId())
                                .build())
                        .items(OrderDetails
                                .builder()
                                .products(Products.builder().id(item.getProducts().getId()).build())
                                .build())
                        .payment(Payment
                                .builder()
                                .id(payment.getId())
                                .build())
                        .amount(amount)
                        .discount(discount)
                        .orderDate(ordertime)
                        .build();
        OrderService.getService().save(orders);
        log.info("Save");
        return orders;
    }
//todo
    public Orders edit(Integer id, Customer customer , OrderDetails item, Payment payment, Double amount, Float discount, LocalDateTime ordertime) throws Exception {
        Orders orders =
                Orders
                        .builder()
                        .id(id)
                        .customer(customer)
                        .items(item)
                        .payment(payment)
                        .amount(amount)
                        .discount(discount)
                        .orderDate(ordertime)
                        .build();

        OrderService.getService().edit(orders);
            return orders;
}

    public Orders remove(Integer id) throws Exception {
        Orders orders=OrderService.getService().findById(id);
        OrderService.getService().remove(id);
        return orders;
    }

    public List<Orders> findAll() throws Exception {
        return OrderService.getService().findAll();
    }

    public List<Orders> findByAll(String searchText) throws Exception {
        return OrderService.getService().findByAll(searchText);
    }

    public Orders findById(Integer id) throws Exception {
        return OrderService.getService().findById(id);
    }
}
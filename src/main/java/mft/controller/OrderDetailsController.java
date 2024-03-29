package mft.controller;


import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.entity.OrderDetails;
import mft.model.entity.Orders;
import mft.model.entity.Products;
import mft.model.service.OrderDetailsService;

import java.util.List;
import java.util.regex.Pattern;
@Log4j
public class OrderDetailsController {
    private static OrderDetailsController controller=new OrderDetailsController();

    private OrderDetailsController() {
    }

    public static OrderDetailsController getController() {
        return controller;
    }

    public OrderDetails save(Integer customer, Integer products, Integer quantity, Double price) throws Exception {
            OrderDetails orderDetails =
                    OrderDetails
                            .builder()
                            .customer(Customer
                                    .builder()
                                    .id(customer)
                                    .build())
                            .products(Products
                                    .builder()
                                    .id(products)
                                    .build())
                            .quantity(quantity)
                            .price(price)
                            .build();
            OrderDetailsService.getService().save(orderDetails);
        log.info("Save OrderDetails");
            return orderDetails;
    }

    public OrderDetails edit(Integer id, Integer customer, Integer products, Integer quantity, Double price) throws Exception {
        OrderDetails orderDetails =
                OrderDetails
                        .builder()
                        .id(id)
                        .customer(Customer
                                .builder()
                                .id(customer)
                                .build())
                        .products(Products
                                .builder()
                                .id(products)
                                .build())
                        .quantity(quantity)
                        .price(price)
                        .build();
        OrderDetailsService.getService().edit(orderDetails);
        log.info("Edit OrderDetails");
            return orderDetails;
    }

    public OrderDetails remove(Integer id) throws Exception {
        OrderDetails orderDetails=OrderDetailsService.getService().findById(id);
        OrderDetailsService.getService().remove(id);
        log.info("Remove OrderDetails");
        return orderDetails;
    }

    public List<OrderDetails> findAll() throws Exception {
        log.info("FindAll OrderDetails");
        return OrderDetailsService.getService().findAll();
    }

    public OrderDetails findById(Integer id) throws Exception {
        log.info("FindById OrderDetails");
        return OrderDetailsService.getService().findById(id);
    }

    public List<OrderDetails> findByCustomerId(int customerId) throws Exception {
        log.info("FindByCustomerId OrderDetails");
        return OrderDetailsService.getService().findByCustomerId(customerId);
    }

    public OrderDetails findSumOrder(int customerId) throws Exception {
        log.info("FindSumOrder OrderDetails");
        return OrderDetailsService.getService().findSumOrder(customerId);
    }

}

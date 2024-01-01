package mft.controller;


import mft.model.entity.OrderDetails;
import mft.model.entity.Products;
import mft.model.service.OrderDetailsService;

import java.util.List;
import java.util.regex.Pattern;

public class OrderDetailsController {
    private static OrderDetailsController controller=new OrderDetailsController();

    private OrderDetailsController() {
    }

    public static OrderDetailsController getController() {
        return controller;
    }

    public OrderDetails save(String products, Integer quantity) throws Exception {
        OrderDetails orderDetails =
                OrderDetails
                        .builder()
                        .products(Products
                                .builder()
                                .name(products)
                                .build())
                        .quantity(quantity)
                        .build();
            OrderDetailsService.getService().save(orderDetails);
            return orderDetails;
    }

    public OrderDetails edit(Integer id, String products, Integer quantity) throws Exception {
        OrderDetails orderDetails =
                OrderDetails
                        .builder()
                        .products(Products
                                .builder()
                                .name(products)
                                .build())
                        .quantity(quantity)
                        .build();
        OrderDetailsService.getService().edit(orderDetails);
            return orderDetails;
    }

    public OrderDetails remove(Integer id) throws Exception {
        OrderDetails orderDetails=OrderDetailsService.getService().findById(id);
        OrderDetailsService.getService().remove(id);
        return orderDetails;
    }

    public List<OrderDetails> findAll() throws Exception {
        return OrderDetailsService.getService().findAll();
    }

    public List<OrderDetails> findByAll(String searchText) throws Exception {
        return OrderDetailsService.getService().findByAll(searchText);
    }

    public OrderDetails findById(Integer id) throws Exception {
        return OrderDetailsService.getService().findById(id);
    }
}

package mft.controller;


import lombok.extern.log4j.Log4j;
import mft.model.entity.OrderDetails;
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

    public OrderDetails save(Products products, Integer quantity) throws Exception {
//        if (Pattern.matches("^[a-zA-Z\\d\\s\\._\\,]{3,30}$" ,products)){
            OrderDetails orderDetails =
                    OrderDetails
                            .builder()
                            .products(Products
                                    .builder()
                                    .id(products.getId())
                                    .build())
                            .quantity(quantity)
                            .build();
            OrderDetailsService.getService().save(orderDetails);
        log.info("Save");
            return orderDetails;
//        }else {
//            throw new Exception("Invalid Data");
//        }
    }

    public OrderDetails edit(Integer id, Products products, Integer quantity) throws Exception {
        OrderDetails orderDetails =
                OrderDetails
                        .builder()
                        .id(id)
                        .products(Products
                                .builder()
                                .id(products.getId())
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

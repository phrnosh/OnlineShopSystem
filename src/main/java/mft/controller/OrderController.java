package mft.controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.*;
import mft.model.entity.enums.OrderStatus;
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

    public Orders save(Integer customer, Double amount, float discount, OrderStatus type, LocalDateTime ordertime) throws Exception {
        Orders orders =
                Orders
                        .builder()
                        .customer(Customer
                                .builder()
                                .id(customer)
                                .build())
                        .amount(amount)
                        .discount(discount)
                        .orderType(type)
                        .orderDate(ordertime)
                        .build();
        OrderService.getService().save(orders);
        log.info("Save Order");
        return orders;
    }

    public Orders edit(Integer id, Integer customer, Double amount, float discount, OrderStatus type, LocalDateTime ordertime) throws Exception {
        Orders orders =
                Orders
                        .builder()
                        .id(id)
                        .customer(Customer
                                .builder()
                                .id(customer)
                                .build())
                        .amount(amount)
                        .discount(discount)
                        .orderType(type)
                        .orderDate(ordertime)
                        .build();

        OrderService.getService().edit(orders);
        log.info("Edit Order");
            return orders;
}

    public Orders remove(Integer id) throws Exception {
        Orders orders=OrderService.getService().findById(id);
        OrderService.getService().remove(id);
        log.info("Remove Order");
        return orders;
    }

    public List<Orders> findAll() throws Exception {
        log.info("FindAll Order");
        return OrderService.getService().findAll();
    }

    public List<Orders> findByAll(String searchText) throws Exception {
        log.info("FindByAll Order");
        return OrderService.getService().findByAll(searchText);
    }

    public Orders findById(Integer id) throws Exception {
        log.info("FindById Order");
        return OrderService.getService().findById(id);
    }

    public List<Orders> findByCustomerId(int customerId) throws Exception {
        log.info("FindByCustomerId Order");
        return OrderService.getService().findByCustomerId(customerId);
    }

    public Orders findAmountOrder(Integer customerId) throws Exception {
        log.info("FindAmountOrder Order");
        return OrderService.getService().findAmountOrder(customerId);
    }

}
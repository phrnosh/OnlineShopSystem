package mft.model.service;


import mft.model.entity.OrderDetails;
import mft.model.repository.OrderDetailsRepository;

import java.util.List;

public class OrderDetailsService {
    private static OrderDetailsService service = new OrderDetailsService();

    private OrderDetailsService() {
    }

    public static OrderDetailsService getService() {
        return service;
    }

    public OrderDetails save(OrderDetails orderDetails) throws Exception {
        try (OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository()) {
            return orderDetailsRepository.save(orderDetails);
        }
    }

    public OrderDetails edit(OrderDetails orderDetails) throws Exception {
        try (OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository()) {
            return orderDetailsRepository.edit(orderDetails);
        }
    }

    public OrderDetails remove(int id) throws Exception {
        try (OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository()) {
            OrderDetails orderDetails = orderDetailsRepository.findById(id);
            if (orderDetails != null) {
                orderDetailsRepository.remove(id);
                return orderDetails;
            } else {
                return null;
            }
        }
    }

    public List<OrderDetails> findAll() throws Exception {
        try (OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository()) {
            return orderDetailsRepository.findAll();
        }
    }

    public List<OrderDetails> findByAll(String searchText) throws Exception {
        try (OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository()) {
            return orderDetailsRepository.findByAll(searchText);
        }
    }

    public OrderDetails findById(int id) throws Exception {
        try (OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository()) {
            return orderDetailsRepository.findById(id);
        }
    }

}

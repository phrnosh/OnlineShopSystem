package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Orders;
import mft.model.repository.OrdersRepository;

import java.util.List;
@Log4j
public class OrderService {
    private static OrderService service = new OrderService();

    private OrderService() {
    }

    public static OrderService getService() {
        return service;
    }

    public Orders save(Orders orders) throws Exception {
        try (OrdersRepository ordersRepository = new OrdersRepository()) {
            return ordersRepository.save(orders);
        }
    }

    public Orders edit(Orders orders) throws Exception {
        try (OrdersRepository ordersRepository = new OrdersRepository()) {
            return ordersRepository.edit(orders);
        }
    }

    public Orders remove(int id) throws Exception {
        try (OrdersRepository ordersRepository = new OrdersRepository()) {
            Orders orders = ordersRepository.findById(id);
            if (orders != null) {
                ordersRepository.remove(id);
                return orders;
            } else {
                return null;
            }
        }
    }

    public List<Orders> findAll() throws Exception {
        try (OrdersRepository ordersRepository = new OrdersRepository()) {
            return ordersRepository.findAll();
        }
    }

    public List<Orders> findByAll(String searchText) throws Exception {
        try (OrdersRepository ordersRepository = new OrdersRepository()) {
            return ordersRepository.findByAll(searchText);
        }
    }

    public Orders findById(int id) throws Exception {
        try (OrdersRepository ordersRepository = new OrdersRepository()) {
            return ordersRepository.findById(id);
        }
    }
}

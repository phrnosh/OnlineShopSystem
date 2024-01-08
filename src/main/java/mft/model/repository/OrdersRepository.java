package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.*;
import mft.model.repository.impl.Da;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Log4j
public class OrdersRepository implements Da<Orders>, AutoCloseable {

    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Orders save(Orders orders) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "select orders_seq.nextval as NEXT_ID from dual"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        orders.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "insert into orders_tbl(id, customer_id, items_id, payment_id, amount, discount, orderdate) values (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, orders.getId());
        preparedStatement.setInt(2, orders.getCustomer().getId());
        preparedStatement.setInt(3, orders.getItems().getProducts().getId());
        preparedStatement.setInt(4, orders.getPayment().getId());
        preparedStatement.setDouble(5, orders.getAmount());
        preparedStatement.setFloat(6, orders.getDiscount());
        preparedStatement.setTimestamp(7, Timestamp.valueOf(orders.getOrderDate()));


        preparedStatement.execute();
        log.info("order repository");
        return orders;
    }

    @Override
    public Orders edit(Orders orders) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "update orders_tbl SET customer_id=?, items_id=?, payment_id=?, amount=?, discount=?, orderdate=? where id=? "
        );
        preparedStatement.setInt(1, orders.getCustomer().getId());
        preparedStatement.setInt(2, orders.getItems().getProducts().getId());
        preparedStatement.setInt(3, orders.getPayment().getId());
        preparedStatement.setDouble(4, orders.getAmount());
        preparedStatement.setFloat(5, orders.getDiscount());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(orders.getOrderDate()));
        preparedStatement.setInt(7, orders.getId());

        preparedStatement.execute();
        return orders;
    }

    @Override
    public Orders remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "Delete FROM orders_tbl WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Orders> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT CUSTOMER_TBL FROM orders_REPORT"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Orders> ordersList = new ArrayList<>();
        while (resultSet.next()) {
            Orders orders =
                    Orders
                            .builder()
                            .id(resultSet.getInt("id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .name(resultSet.getString("customer_name"))
                                    .family(resultSet.getString("customer_family"))
                                    .build())
                            .items(OrderDetails
                                    .builder()
                                    .id(resultSet.getInt("item_id"))
                                    .products(Products
                                            .builder()
                                            .name(resultSet.getString("name"))
                                            .price(resultSet.getDouble("price"))
                                            .build())
                                    .quantity(resultSet.getInt("quantity"))
                                    .build())
                            .payment(Payment
                                    .builder()
                                    .totalCost(resultSet.getDouble("totalcost"))
                                    .build())
                            .discount(resultSet.getInt("discount"))
                            .orderDate(resultSet.getTimestamp("orderDate").toLocalDateTime())
                            .build();
            ordersList.add(orders);
        }
        return ordersList;
    }

    public List<Orders> findByAll(String searchText) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM orders_REPORT WHERE item_id LIKE ? or customer_ID LIKE ? "
        );
        preparedStatement.setString(1, searchText + "%");
        preparedStatement.setString(2, searchText + "%");

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Orders> ordersList = new ArrayList<>();
        while (resultSet.next()) {
            Orders orders =
                    Orders
                            .builder()
                            .id(resultSet.getInt("id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .name(resultSet.getString("customer_name"))
                                    .family(resultSet.getString("customer_family"))
                                    .build())
                            .items(OrderDetails
                                    .builder()
                                    .id(resultSet.getInt("item_id"))
                                    .products(Products
                                            .builder()
                                            .name(resultSet.getString("name"))
                                            .price(resultSet.getDouble("price"))
                                            .build())
                                    .quantity(resultSet.getInt("quantity"))
                                    .build())
                            .payment(Payment
                                    .builder()
                                    .totalCost(resultSet.getDouble("totalcost"))
                                    .build())
                            .discount(resultSet.getInt("discount"))
                            .orderDate(resultSet.getTimestamp("orderDate").toLocalDateTime())
                            .build();
            ordersList.add(orders);
        }
        return ordersList;
    }

    @Override
    public Orders findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM orders_REPORT WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();

        Orders orders = null;
        while (resultSet.next()) {
            orders =
                    Orders
                            .builder()
                            .id(resultSet.getInt("id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .name(resultSet.getString("customer_name"))
                                    .family(resultSet.getString("customer_family"))
                                    .build())
                            .items(OrderDetails
                                    .builder()
                                    .id(resultSet.getInt("item_id"))
                                    .products(Products
                                            .builder()
                                            .name(resultSet.getString("name"))
                                            .price(resultSet.getDouble("price"))
                                            .build())
                                    .quantity(resultSet.getInt("quantity"))
                                    .build())
                            .payment(Payment
                                    .builder()
                                    .totalCost(resultSet.getDouble("totalcost"))
                                    .build())
                            .discount(resultSet.getInt("discount"))
                            .orderDate(resultSet.getTimestamp("orderDate").toLocalDateTime())
                            .build();
        }
        return orders;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

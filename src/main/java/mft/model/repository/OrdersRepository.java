package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.*;
import mft.model.entity.enums.OrderStatus;
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
                "SELECT ORDERS_SEQ.NEXTVAL AS NEXT_ID FROM DUAL"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        orders.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO ORDERS_TBL (ID, CUSTOMER_ID, AMOUNT, DISCOUNT, ORDER_TYPE, ORDERDATE) VALUES (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, orders.getId());
        preparedStatement.setInt(2, orders.getCustomer().getId());
        preparedStatement.setDouble(3, orders.getAmount());
        preparedStatement.setFloat(4, orders.getDiscount());
        preparedStatement.setString(5, String.valueOf(orders.getOrderType()));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(orders.getOrderDate()));

        preparedStatement.execute();
        log.info("Order Repository");
        return orders;
    }

    @Override
    public Orders edit(Orders orders) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE ORDERS_TBL SET CUSTOMER_ID=?, AMOUNT=?, DISCOUNT=?, ORDER_TYPE=?, ORDERDATE=? WHERE ID=? "
        );
        preparedStatement.setInt(1, orders.getCustomer().getId());
        preparedStatement.setDouble(2, orders.getAmount());
        preparedStatement.setFloat(3, orders.getDiscount());
        preparedStatement.setString(4, String.valueOf(orders.getOrderType()));
        preparedStatement.setTimestamp(5, Timestamp.valueOf(orders.getOrderDate()));
        preparedStatement.setInt(6, orders.getId());

        preparedStatement.execute();
        log.info("Order Repository");
        return orders;
    }

    @Override
    public Orders remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ORDERS_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Order Repository");
        return null;
    }

    @Override
    public List<Orders> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ORDERS_REPORT ORDER BY ORDERDATE"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Orders> ordersList = new ArrayList<>();
        while (resultSet.next()) {
            Orders orders =
                    Orders
                            .builder()
                            .id(resultSet.getInt("order_id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .name(resultSet.getString("customer_name"))
                                    .family(resultSet.getString("customer_family"))
                                    .build())
                            .orderDetails(OrderDetails
                                    .builder()
                                    .products(Products
                                            .builder()
                                            .id(resultSet.getInt("products_id"))
                                            .build())
                                    .build())
                            .amount(resultSet.getDouble("amount"))
                            .discount(resultSet.getFloat("discount"))
                            .orderDate(resultSet.getTimestamp("orderDate").toLocalDateTime())
                            .build();
            ordersList.add(orders);
        }
        log.info("Order Repository");
        return ordersList;
    }


    public List<Orders> findByAll(String searchText) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ORDERS_REPORT WHERE CUSTOMER_NAME LIKE ? OR CUSTOMER_ID LIKE ? OR CUSTOMER_FAMILY LIKE ? OR PRODUCTS_ID LIKE ?"
        );
        preparedStatement.setString(1, searchText + "%");
        preparedStatement.setString(2, searchText + "%");
        preparedStatement.setString(3, searchText + "%");
        preparedStatement.setString(4, searchText + "%");

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Orders> ordersList = new ArrayList<>();
        while (resultSet.next()) {
            Orders orders =
                    Orders
                            .builder()
                            .id(resultSet.getInt("order_id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .name(resultSet.getString("customer_name"))
                                    .family(resultSet.getString("customer_family"))
                                    .build())
                            .orderDetails(OrderDetails
                                    .builder()
                                    .products(Products
                                            .builder()
                                            .id(resultSet.getInt("products_id"))
                                            .build())
                                    .build())
                            .amount(resultSet.getDouble("amount"))
                            .discount(resultSet.getFloat("discount"))
                            .orderDate(resultSet.getTimestamp("orderDate").toLocalDateTime())
                            .build();
            ordersList.add(orders);
        }
        log.info("Order Repository");
        return ordersList;
    }

    @Override
    public Orders findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ORDERS_TBL WHERE ID=?"
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
                                    .build())
                            .amount(resultSet.getDouble("amount"))
                            .discount(resultSet.getFloat("discount"))
                            .orderType(OrderStatus.valueOf(resultSet.getString("order_Type")))
                            .orderDate(resultSet.getTimestamp("orderDate").toLocalDateTime())
                            .build();
        }
        log.info("Order Repository");
        return orders;
    }

    public List<Orders> findByCustomerId(int customerId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ORDERS_TBL WHERE CUSTOMER_ID=?"
        );
        preparedStatement.setInt(1, customerId);
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
                                    .build())
                            .amount(resultSet.getDouble("amount"))
                            .discount(resultSet.getFloat("discount"))
                            .orderType(OrderStatus.valueOf(resultSet.getString("order_Type")))
                            .orderDate(resultSet.getTimestamp("orderDate").toLocalDateTime())
                            .build();
            ordersList.add(orders);
        }
        log.info("Order Repository");
        return ordersList;
    }


    public Orders findAmountOrder(int customerId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT SUM(AMOUNT - DISCOUNT) AS AMOUNT FROM ORDERS_TBL WHERE CUSTOMER_ID =?"
        );
        preparedStatement.setInt(1,customerId);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();

        Orders orders = null;
        while (resultSet.next()) {
            orders =
                    Orders
                            .builder()
                            .amount(resultSet.getDouble("amount"))
                            .build();
        }
        log.info("Order Repository");
        return orders;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

package mft.model.repository;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.entity.OrderDetails;
import mft.model.entity.Orders;
import mft.model.entity.Products;
import mft.model.repository.impl.Da;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class OrderDetailsRepository implements Da<OrderDetails>, AutoCloseable{

    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public OrderDetails save(OrderDetails orderDetails) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "select orderDetails_seq.nextval as NEXT_ID from dual"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        orderDetails.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "insert into orderDetails_tbl(id, customer_id, order_id, products_id, quantity, price) values (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, orderDetails.getId());
        preparedStatement.setInt(2, orderDetails.getCustomer().getId());
        preparedStatement.setInt(3, orderDetails.getOrder().getId());
        preparedStatement.setInt(4, orderDetails.getProducts().getId());
        preparedStatement.setInt(5, orderDetails.getQuantity());
        preparedStatement.setDouble(6, orderDetails.getPrice());

        preparedStatement.execute();
        log.info("orderDetails repository");
        return orderDetails;
    }

    @Override
    public OrderDetails edit(OrderDetails orderDetails) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "update orderDetails_tbl SET customer_id=?, order_id=?, products_id=?, quantity=?, price=? where id=? "
        );
        preparedStatement.setInt(1, orderDetails.getCustomer().getId());
        preparedStatement.setInt(2, orderDetails.getOrder().getId());
        preparedStatement.setInt(3, orderDetails.getProducts().getId());
        preparedStatement.setInt(4, orderDetails.getQuantity());
        preparedStatement.setDouble(5, orderDetails.getPrice());
        preparedStatement.setInt(6, orderDetails.getId());


        preparedStatement.execute();
        log.info("orderDetails repository");
        return orderDetails;
    }

    @Override
    public OrderDetails remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "Delete FROM orderDetails_tbl WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("orderDetails repository");
        return null;
    }

    @Override
    public List<OrderDetails> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM orderDetails_tbl"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        while (resultSet.next()) {
            OrderDetails orderDetails =
                    OrderDetails
                            .builder()
                            .id(resultSet.getInt("id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .build())
                            .order(Orders
                                    .builder()
                                    .id(resultSet.getInt("order_id"))
                                    .build())
                            .products(Products
                                    .builder()
                                    .id(resultSet.getInt("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .price(resultSet.getDouble("price"))
                            .build();
            orderDetailsList.add(orderDetails);
        }
        log.info("orderDetails repository");
        return orderDetailsList;
    }

    public List<OrderDetails> findByAll(String searchText) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM orderDetails_tbl WHERE ID LIKE ? or PRODUCTS_ID LIKE ? "
        );
        preparedStatement.setString(1, searchText + "%");
        preparedStatement.setString(2, searchText + "%");

        ResultSet resultSet = preparedStatement.executeQuery();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        while (resultSet.next()) {
            OrderDetails orderDetails =
                    OrderDetails
                            .builder()
                            .id(resultSet.getInt("id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .build())
                            .order(Orders
                                    .builder()
                                    .id(resultSet.getInt("order_id"))
                                    .build())
                            .products(Products
                                    .builder()
                                    .id(resultSet.getInt("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .price(resultSet.getDouble("price"))
                            .build();
            orderDetailsList.add(orderDetails);
        }
        log.info("orderDetails repository");
        return orderDetailsList;
    }

    @Override
    public OrderDetails findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM orderDetails_tbl WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();

        OrderDetails orderDetails = null;
        while (resultSet.next()) {
            orderDetails =
                    OrderDetails
                            .builder()
                            .id(resultSet.getInt("id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .build())
                            .order(Orders
                                    .builder()
                                    .id(resultSet.getInt("order_id"))
                                    .build())
                            .products(Products
                                    .builder()
                                    .id(resultSet.getInt("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .price(resultSet.getDouble("price"))
                            .build();
        }
        log.info("orderDetails repository");
        return orderDetails;
    }

    public List<OrderDetails> findByCustomerId(int customerId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM orderDetails_tbl where customer_id=?"
        );
        preparedStatement.setInt(1, customerId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        while (resultSet.next()) {
            OrderDetails orderDetails =
                    OrderDetails
                            .builder()
                            .id(resultSet.getInt("id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .build())
                            .order(Orders
                                    .builder()
                                    .id(resultSet.getInt("order_id"))
                                    .build())
                            .products(Products
                                    .builder()
                                    .id(resultSet.getInt("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .price(resultSet.getDouble("price"))
                            .build();
            orderDetailsList.add(orderDetails);
        }
        return orderDetailsList;
    }

    public OrderDetails findSumOrder(int customerId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT sum(quantity * price) as price FROM orderDetails_tbl where customer_id =?"
        );
        preparedStatement.setInt(1,customerId);
//todo
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();

        OrderDetails orderDetails = null;
        while (resultSet.next()) {
            orderDetails =
                    OrderDetails
                            .builder()
                            .price(resultSet.getDouble("price"))
                            .build();
        }
        return orderDetails;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

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
                "SELECT ORDERDETAILS_SEQ.NEXTVAL AS NEXT_ID FROM DUAL"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        orderDetails.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO ORDERDETAILS_TBL (ID, CUSTOMER_ID, PRODUCTS_ID, QUANTITY, PRICE) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, orderDetails.getId());
        preparedStatement.setInt(2, orderDetails.getCustomer().getId());
//        preparedStatement.setInt(3, orderDetails.getOrder().getId());
        preparedStatement.setInt(3, orderDetails.getProducts().getId());
        preparedStatement.setInt(4, orderDetails.getQuantity());
        preparedStatement.setDouble(5, orderDetails.getPrice());

        preparedStatement.execute();
        log.info("OrderDetails Repository");
        return orderDetails;
    }

    @Override
    public OrderDetails edit(OrderDetails orderDetails) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE ORDERDETAILS_TBL SET CUSTOMER_ID=?, PRODUCTS_ID=?, QUANTITY=?, PRICE=? WHERE ID=? "
        );
        preparedStatement.setInt(1, orderDetails.getCustomer().getId());
//        preparedStatement.setInt(2, orderDetails.getOrder().getId());
        preparedStatement.setInt(2, orderDetails.getProducts().getId());
        preparedStatement.setInt(3, orderDetails.getQuantity());
        preparedStatement.setDouble(4, orderDetails.getPrice());
        preparedStatement.setInt(5, orderDetails.getId());


        preparedStatement.execute();
        log.info("OrderDetails Repository");
        return orderDetails;
    }

    @Override
    public OrderDetails remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ORDERDETAILS_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("OrderDetails Repository");
        return null;
    }

    @Override
    public List<OrderDetails> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ORDERDETAILS_TBL"
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
                            .products(Products
                                    .builder()
                                    .id(resultSet.getInt("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .price(resultSet.getDouble("price"))
                            .build();
            orderDetailsList.add(orderDetails);
        }
        log.info("OrderDetails Repository");
        return orderDetailsList;
    }

    @Override
    public OrderDetails findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ORDERDETAILS_TBL WHERE ID=?"
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
                            .products(Products
                                    .builder()
                                    .id(resultSet.getInt("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .price(resultSet.getDouble("price"))
                            .build();
        }
        log.info("OrderDetails Repository");
        return orderDetails;
    }

    public List<OrderDetails> findByCustomerId(int customerId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ORDERDETAILS_TBL WHERE CUSTOMER_ID=?"
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
                            .products(Products
                                    .builder()
                                    .id(resultSet.getInt("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .price(resultSet.getDouble("price"))
                            .build();
            orderDetailsList.add(orderDetails);
        }
        log.info("OrderDetails Repository");
        return orderDetailsList;
    }

    public OrderDetails findSumOrder(int customerId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT SUM(QUANTITY * PRICE) AS PRICE FROM ORDERDETAILS_TBL WHERE CUSTOMER_ID =?"
        );
        preparedStatement.setInt(1, customerId);
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
        log.info("OrderDetails Repository");
        return orderDetails;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

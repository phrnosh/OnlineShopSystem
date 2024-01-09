package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.OrderDetails;
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
                "insert into orderDetails_tbl(id, products_id, price, quantity) values (?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, orderDetails.getId());
        preparedStatement.setInt(2, orderDetails.getProducts().getId());
        preparedStatement.setInt(3, orderDetails.getQuantity());
        preparedStatement.setDouble(4, orderDetails.getProducts().getPrice());


        preparedStatement.execute();
        log.info("orderDetails repository");
        return orderDetails;
    }

    @Override
    public OrderDetails edit(OrderDetails orderDetails) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "update orderDetails_tbl SET price=?, products_id=?, quantity=? where id=? "
        );
        preparedStatement.setInt(1, orderDetails.getProducts().getId());
        preparedStatement.setInt(2, orderDetails.getQuantity());
        preparedStatement.setDouble(3, orderDetails.getProducts().getPrice());
        preparedStatement.setInt(4, orderDetails.getId());

        preparedStatement.execute();
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
                            .products(Products
                                    .builder()
                                    .name(resultSet.getString("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .products(Products
                                    .builder()
                                    .price(resultSet.getDouble("price"))
                                    .build())
                            .build();
            orderDetailsList.add(orderDetails);
        }
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
                            .products(Products
                                    .builder()
                                    .name(resultSet.getString("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .products(Products
                                    .builder()
                                    .price(resultSet.getDouble("price"))
                                    .build())
                            .build();
            orderDetailsList.add(orderDetails);
        }
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
                            .products(Products
                                    .builder()
                                    .name(resultSet.getString("products_id"))
                                    .build())
                            .quantity(resultSet.getInt("quantity"))
                            .products(Products
                                    .builder()
                                    .price(resultSet.getDouble("price"))
                                    .build())
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

package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Products;
import mft.model.repository.impl.Da;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class ProductRepository implements Da<Products>,AutoCloseable {

    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Products save(Products products) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT PRODUCT_SEQ.NEXTVAL AS NEXT_ID FROM DUAL"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        products.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PRODUCT_TBL (ID, NAME, BRAND, PRODUCTSIZE, PRICE, DESCRIPTION) VALUES (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, products.getId());
        preparedStatement.setString(2, products.getName());
        preparedStatement.setString(3, products.getBrand());
        preparedStatement.setString(4, products.getSize());
        preparedStatement.setDouble(5, products.getPrice());
        preparedStatement.setString(6, products.getDescription());

        preparedStatement.execute();
        log.info("Product Repository");
        return products;
    }

    @Override
    public Products edit(Products products) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE PRODUCT_TBL SET NAME=?, BRAND=?, PRODUCTSIZE=?, PRICE=?, DESCRIPTION=? WHERE ID=? "
        );
        preparedStatement.setString(1, products.getName());
        preparedStatement.setString(2, products.getBrand());
        preparedStatement.setString(3, products.getSize());
        preparedStatement.setDouble(4, products.getPrice());
        preparedStatement.setString(5, products.getDescription());
        preparedStatement.setInt(6, products.getId());

        preparedStatement.execute();
        log.info("Product Repository");
        return products;
    }

    @Override
    public Products remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PRODUCT_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Product Repository");
        return null;
    }

    @Override
    public List<Products> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL ORDER BY ID"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Products> productsList = new ArrayList<>();
        while (resultSet.next()) {
            Products products =
                    Products
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .brand(resultSet.getString("brand"))
                            .size(resultSet.getString("productSize"))
                            .price(resultSet.getDouble("price"))
                            .description(resultSet.getString("description"))
                            .build();
            productsList.add(products);
        }
        log.info("Product Repository");
        return productsList;
    }

    public List<Products> findByAll(String searchText) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL WHERE NAME LIKE ? OR BRAND LIKE ? OR PRICE LIKE ? OR DESCRIPTION LIKE ? "
        );
        preparedStatement.setString(1, searchText + "%");
        preparedStatement.setString(2, searchText + "%");
        preparedStatement.setString(3, searchText + "%");
        preparedStatement.setString(4, searchText + "%");

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Products> productsList = new ArrayList<>();
        while (resultSet.next()) {
            Products products =
                    Products
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .brand(resultSet.getString("brand"))
                            .size(resultSet.getString("productSize"))
                            .price(resultSet.getDouble("price"))
                            .description(resultSet.getString("description"))
                            .build();
            productsList.add(products);
        }
        log.info("Product Repository");
        return productsList;
    }

    @Override
    public Products findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();

        Products products = null;
        while (resultSet.next()) {
            products =
                    Products
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .brand(resultSet.getString("brand"))
                            .size(resultSet.getString("productSize"))
                            .price(resultSet.getDouble("price"))
                            .description(resultSet.getString("description"))
                            .build();
        }
        log.info("Product Repository");
        return products;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

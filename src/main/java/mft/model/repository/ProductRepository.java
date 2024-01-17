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
                "select product_seq.nextval as NEXT_ID from dual"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        products.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "insert into PRODUCT_TBL(id, name, brand, productSize, price, description) values (?, ?, ?, ?, ?, ?)"
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
                "update PRODUCT_TBL SET name=?, brand=?, productSize=?, price=?, description=? where id=? "
        );
        preparedStatement.setString(1, products.getName());
        preparedStatement.setString(2, products.getBrand());
        preparedStatement.setString(3, products.getSize());
        preparedStatement.setDouble(4, products.getPrice());
        preparedStatement.setString(5, products.getDescription());
        preparedStatement.setInt(6, products.getId());

        preparedStatement.execute();
        return products;
    }

    @Override
    public Products remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "Delete FROM PRODUCT_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
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
        return productsList;
    }

    public List<Products> findByAll(String searchText) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL WHERE NAME LIKE ? or BRAND LIKE ? OR PRICE LIKE ? OR DESCRIPTION LIKE ? "
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
        return products;
    }

    public Products findByName(String name) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON_TBL WHERE NAME LIKE ?"
        );
        preparedStatement.setString(1, name + "%");
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
        return products;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

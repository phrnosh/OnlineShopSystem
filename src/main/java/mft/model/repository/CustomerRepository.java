package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.repository.impl.Da;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CustomerRepository implements Da<Customer>, AutoCloseable{

        private PreparedStatement preparedStatement;
        private Connection connection;
    @Override
    public Customer save(Customer customer) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "select customer_seq.nextval as NEXT_ID from dual"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        customer.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "insert into CUSTOMER_TBL(id, name, family,username, password, address, phoneNumber, email, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getFamily());
        preparedStatement.setString(4, customer.getUsername());
        preparedStatement.setString(5, customer.getPassword());
        preparedStatement.setString(6, customer.getAddress());
        preparedStatement.setString(7, customer.getPhoneNumber().trim());
        preparedStatement.setString(8, customer.getEmail());
        preparedStatement.setBoolean(9, customer.isStatus());
        preparedStatement.execute();
        log.info("customer repository");
        return customer;
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "update CUSTOMER_TBL SET name=?, family=?, password=?, address=?, phoneNumber=?, email=?, status=? where username=? or id=?"
        );
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getFamily());
        preparedStatement.setString(3, customer.getPassword());
        preparedStatement.setString(4, customer.getAddress());
        preparedStatement.setString(5, customer.getPhoneNumber());
        preparedStatement.setString(6, customer.getEmail());
        preparedStatement.setBoolean(7, customer.isStatus());
        preparedStatement.setString(8, customer.getUsername());
        preparedStatement.setInt(9, customer.getId());
        preparedStatement.execute();
        return customer;
    }

    @Override
    public Customer remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "Delete FROM CUSTOMER_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM CUSTOMER_TBL"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer =
                    Customer
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .family(resultSet.getString("family"))
                            .username(resultSet.getString("username"))
                            .password(resultSet.getString("password"))
                            .address(resultSet.getString("address"))
                            .phoneNumber(resultSet.getString("phoneNumber").trim())
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("status"))
                            .build();
            customerList.add(customer);
        }
        return customerList;
    }

    public List<Customer> findByAll(String searchText) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM CUSTOMER_TBL WHERE ID LIKE ? or USERNAME LIKE ? " +
                        "or NAME LIKE ? OR FAMILY LIKE ? OR ADDRESS LIKE ? OR PHONENUMBER LIKE ? OR EMAIL LIKE ?"
        );
        preparedStatement.setString(1, searchText + "%");
        preparedStatement.setString(2, searchText + "%");
        preparedStatement.setString(3, searchText + "%");
        preparedStatement.setString(4, searchText + "%");
        preparedStatement.setString(5, searchText + "%");
        preparedStatement.setString(6, searchText + "%");
        preparedStatement.setString(7, searchText + "%");

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer =
                    Customer
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .family(resultSet.getString("family"))
                            .username(resultSet.getString("username"))
                            .password(resultSet.getString("password"))
                            .address(resultSet.getString("address"))
                            .phoneNumber(resultSet.getString("phoneNumber").trim())
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("status"))
                            .build();
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM CUSTOMER_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();

        Customer customer = null;
        while (resultSet.next()) {
            customer =
                    Customer
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .family(resultSet.getString("family"))
                            .username(resultSet.getString("username").trim())
                            .password(resultSet.getString("password").trim())
                            .address(resultSet.getString("address"))
                            .phoneNumber(resultSet.getString("phoneNumber").trim())
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("status"))
                            .build();
        }
        return customer;
    }



        public Customer findByUsername(String username) throws Exception {
            connection = JdbcProvider.getJdbcProvider().getConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM CUSTOMER_TBL WHERE USERNAME=?"
            );
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            Customer customer = null;
            while (resultSet.next()) {
                customer =
                        Customer
                                .builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .family(resultSet.getString("family"))
                                .username(resultSet.getString("username"))
                                .password(resultSet.getString("password").trim())
                                .address(resultSet.getString("address"))
                                .phoneNumber(resultSet.getString("phoneNumber").trim())
                                .email(resultSet.getString("email"))
                                .password(resultSet.getString("status"))
                                .build();
            }
            return customer;
        }

        public Customer findByUsernameAndPassword(String username, String password) throws Exception {
            connection = JdbcProvider.getJdbcProvider().getConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM CUSTOMER_TBL WHERE USERNAME=? AND PASSWORD=? "
            );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            Customer customer = null;
            while (resultSet.next()) {
                customer =
                        Customer
                                .builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .family(resultSet.getString("family"))
                                .username(resultSet.getString("username").trim())
                                .password(resultSet.getString("password").trim())
                                .address(resultSet.getString("address"))
                                .phoneNumber(resultSet.getString("phoneNumber").trim())
                                .email(resultSet.getString("email"))
                                .password(resultSet.getString("status"))
                                .build();
            }
            return customer;
        }

        @Override
        public void close() throws Exception {
            preparedStatement.close();
            connection.close();
        }
    }

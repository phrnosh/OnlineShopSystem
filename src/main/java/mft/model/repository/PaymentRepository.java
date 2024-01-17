package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.entity.Orders;
import mft.model.entity.Payment;
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
public class PaymentRepository implements Da<Payment>, AutoCloseable{

    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Payment save(Payment payment) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "select payment_seq.nextval as NEXT_ID from dual"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        payment.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "insert into payment_tbl(id, customer_id, totalcost, paymentdetails, type, order_type, paymentdate) values (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setInt(2, payment.getCustomer().getId());
        preparedStatement.setDouble(3, payment.getTotalCost());
        preparedStatement.setString(4, payment.getPaymentDetails());
        preparedStatement.setString(5, payment.getPaymentType());
        preparedStatement.setString(6, String.valueOf(payment.getOrderType()));
        preparedStatement.setTimestamp(7, Timestamp.valueOf(payment.getPaymentTimeStamp()));

        preparedStatement.execute();
        log.info("payment repository");
        return payment;
    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "update payment_tbl SET customer_id=?, totalcost=?, paymentdetails=?, type=?, order_type=?, paymentdate=? where id=? "
        );

        preparedStatement.setDouble(1, payment.getTotalCost());
        preparedStatement.setInt(2, payment.getCustomer().getId());
        preparedStatement.setString(3, payment.getPaymentDetails());
        preparedStatement.setString(4, payment.getPaymentType());
        preparedStatement.setString(5, String.valueOf(payment.getOrderType()));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(payment.getPaymentTimeStamp()));
        preparedStatement.setInt(7, payment.getId());

        preparedStatement.execute();
        return payment;
    }

    @Override
    public Payment remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "Delete FROM payment_tbl WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Payment> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PAYMENT_REPORT ORDER BY payment_date"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment =
                    Payment
                            .builder()
                            .id(resultSet.getInt("payment_id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .name(resultSet.getString("customer_name"))
                                    .family(resultSet.getString("customer_family"))
                                    .build())
                            .totalCost(resultSet.getDouble("total_cost"))
                            .PaymentType(resultSet.getString("payment_type"))
                            .PaymentTimeStamp(resultSet.getTimestamp("payment_date").toLocalDateTime())
                            .build();
            paymentList.add(payment);
        }
        return paymentList;
    }

    public List<Payment> findByAll(String searchText) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PAYMENT_REPORT WHERE payment_id LIKE ? or CUSTOMER_ID LIKE ? or payment_type LIKE ?"
        );
        preparedStatement.setString(1, searchText + "%");
        preparedStatement.setString(2, searchText + "%");
        preparedStatement.setString(3, searchText + "%");

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment =
                    Payment
                            .builder()
                            .id(resultSet.getInt("payment_id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .name(resultSet.getString("customer_name"))
                                    .family(resultSet.getString("customer_family"))
                                    .build())
                            .totalCost(resultSet.getDouble("total_cost"))
                            .PaymentType(resultSet.getString("payment_type"))
                            .PaymentTimeStamp(resultSet.getTimestamp("payment_date").toLocalDateTime())
                            .build();
            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public Payment findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM payment_tbl WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();

        Payment payment = null;
        while (resultSet.next()) {
            payment =
                    Payment
                            .builder()
                            .id(resultSet.getInt("id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .build())
                            .totalCost(resultSet.getDouble("totalCost"))
                            .PaymentDetails(resultSet.getString("PaymentDetails"))
                            .PaymentType(resultSet.getString("Type"))
                            .orderType(OrderStatus.valueOf(resultSet.getString("order_type")))
                            .PaymentTimeStamp(resultSet.getTimestamp("PaymentDate").toLocalDateTime())
                            .build();
        }
        return payment;
    }

    public List<Payment> findByCustomerId(int customerId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM payment_tbl where customer_id=?"
        );
        preparedStatement.setInt(1, customerId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment =
                    Payment
                            .builder()
                            .id(resultSet.getInt("id"))
                            .customer(Customer
                                    .builder()
                                    .id(resultSet.getInt("customer_id"))
                                    .build())
                            .totalCost(resultSet.getDouble("totalCost"))
                            .PaymentDetails(resultSet.getString("PaymentDetails"))
                            .PaymentType(resultSet.getString("Type"))
                            .orderType(OrderStatus.valueOf(resultSet.getString("order_type")))
                            .PaymentTimeStamp(resultSet.getTimestamp("PaymentDate").toLocalDateTime())
                            .build();
            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

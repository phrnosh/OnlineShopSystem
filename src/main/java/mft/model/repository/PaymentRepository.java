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
                "SELECT PAYMENT_SEQ.NEXTVAL AS NEXT_ID FROM DUAL"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        payment.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PAYMENT_TBL (ID, CUSTOMER_ID, TOTALCOST, PAYMENTDETAILS, TYPE, ORDER_TYPE, PAYMENTDATE) VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setInt(2, payment.getCustomer().getId());
        preparedStatement.setDouble(3, payment.getTotalCost());
        preparedStatement.setString(4, payment.getPaymentDetails());
        preparedStatement.setString(5, payment.getPaymentType());
        preparedStatement.setString(6, String.valueOf(payment.getOrderType()));
        preparedStatement.setTimestamp(7, Timestamp.valueOf(payment.getPaymentTimeStamp()));

        preparedStatement.execute();
        log.info("Payment Repository");
        return payment;
    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE PAYMENT_TBL SET CUSTOMER_ID=?, TOTALCOST=?, PAYMENTDETAILS=?, TYPE=?, ORDER_TYPE=?, PAYMENTDATE=? WHERE ID=? "
        );

        preparedStatement.setDouble(1, payment.getTotalCost());
        preparedStatement.setInt(2, payment.getCustomer().getId());
        preparedStatement.setString(3, payment.getPaymentDetails());
        preparedStatement.setString(4, payment.getPaymentType());
        preparedStatement.setString(5, String.valueOf(payment.getOrderType()));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(payment.getPaymentTimeStamp()));
        preparedStatement.setInt(7, payment.getId());

        preparedStatement.execute();
        log.info("Payment Repository");
        return payment;
    }

    @Override
    public Payment remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PAYMENT_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Payment Repository");
        return null;
    }

    @Override
    public List<Payment> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PAYMENT_REPORT ORDER BY PAYMENT_DATE"
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
        log.info("Payment Repository");
        return paymentList;
    }

    public List<Payment> findByAll(String searchText) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PAYMENT_REPORT WHERE PAYMENT_ID LIKE ? OR CUSTOMER_ID LIKE ? OR PAYMENT_TYPE LIKE ?"
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
        log.info("Payment Repository");
        return paymentList;
    }

    @Override
    public Payment findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PAYMENT_TBL WHERE ID=?"
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
        log.info("Payment Repository");
        return payment;
    }

    public List<Payment> findByCustomerId(int customerId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PAYMENT_TBL WHERE CUSTOMER_ID=?"
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
        log.info("Payment Repository");
        return paymentList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

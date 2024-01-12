package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.OrderDetails;
import mft.model.entity.Payment;
import mft.model.entity.Products;
import mft.model.entity.enums.PaymentType;
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
                "insert into payment_tbl(id, totalcost, paymentdetails, type, paymentdate) values (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setDouble(2, payment.getTotalCost());
        preparedStatement.setString(3, payment.getPaymentDetails());
        preparedStatement.setString(4, payment.getPaymentType().name());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(payment.getPaymentTimeStamp()));

        preparedStatement.execute();
        log.info("payment repository");
        return payment;
    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "update payment_tbl SET totalcost=?, paymentdetails=?, type=?, paymentdate=? where id=? "
        );

        preparedStatement.setDouble(1, payment.getTotalCost());
        preparedStatement.setString(2, payment.getPaymentDetails());
        preparedStatement.setString(3, payment.getPaymentType().name());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(payment.getPaymentTimeStamp()));
        preparedStatement.setInt(5, payment.getId());

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
                "SELECT * FROM payment_tbl"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment =
                    Payment
                            .builder()
                            .id(resultSet.getInt("id"))
                            .totalCost(resultSet.getDouble("totalCost"))
                            .PaymentDetails(resultSet.getString("paymentDetails"))
                            .PaymentType(PaymentType.valueOf(resultSet.getString("type")))
                            .PaymentTimeStamp(resultSet.getTimestamp("paymentDate").toLocalDateTime())

                            .build();
            paymentList.add(payment);
        }
        return paymentList;
    }
//
//    public List<Payment> findByAll(String searchText) throws Exception {
//        connection = JdbcProvider.getJdbcProvider().getConnection();
//        preparedStatement = connection.prepareStatement(
//                "SELECT * FROM payment_tbl WHERE ID LIKE ? or PRODUCTS_ID LIKE ? "
//        );
//        preparedStatement.setString(1, searchText);
//        preparedStatement.setString(2, searchText);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//        List<Payment> paymentList = new ArrayList<>();
//        while (resultSet.next()) {
//            Payment payment =
//                    Payment
//                            .builder()
//                            .id(resultSet.getInt("id"))
//                            .totalCost(resultSet.getDouble("totalCost"))
//                            .PaymentType(PaymentType.valueOf(resultSet.getString("PaymentType")))
//                            .PaymentDetails(resultSet.getString("PaymentDetails"))
//                            .PaymentTimeStamp(resultSet.getTimestamp("PaymentTimeStamp").toLocalDateTime())
//                            .build();
//            paymentList.add(payment);
//        }
//        return paymentList;
//    }

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
                            .totalCost(resultSet.getDouble("totalCost"))
                            .PaymentDetails(resultSet.getString("PaymentDetails"))
                            .PaymentType(PaymentType.valueOf(resultSet.getString("Type")))
                            .PaymentTimeStamp(resultSet.getTimestamp("PaymentDate").toLocalDateTime())
                            .build();
        }
        return payment;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

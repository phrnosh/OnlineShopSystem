package mft.controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.entity.OrderDetails;
import mft.model.entity.Payment;
import mft.model.entity.enums.OrderStatus;
import mft.model.service.OrderDetailsService;
import mft.model.service.PaymentService;

import java.time.LocalDateTime;
import java.util.List;
@Log4j
public class PaymentController {
    private static PaymentController controller=new PaymentController();

    private PaymentController() {
    }

    public static PaymentController getController() {
        return controller;
    }

    public Payment save(Integer customer, Double totalCost, String paymentDetails, String type, OrderStatus orderType, LocalDateTime paymentDate) throws Exception {
        Payment payment =
                Payment
                        .builder()
                        .customer(Customer
                                .builder()
                                .id(customer)
                                .build())
                        .totalCost(totalCost)
                        .PaymentDetails(paymentDetails)
                        .PaymentType(type)
                        .orderType(orderType)
                        .PaymentTimeStamp(paymentDate)
                        .build();
        PaymentService.getService().save(payment);
        log.info("Save Payment");
        return payment;
    }

    public Payment edit(Integer id, Integer customer, Double totalCost, String paymentDetails, String type, OrderStatus orderType, LocalDateTime paymentDate) throws Exception {
        Payment payment =
                Payment
                        .builder()
                        .id(id)
                        .customer(Customer
                                .builder()
                                .id(customer)
                                .build())
                        .totalCost(totalCost)
                        .PaymentDetails(paymentDetails)
                        .PaymentType(type)
                        .orderType(orderType)
                        .PaymentTimeStamp(paymentDate)
                        .build();
        PaymentService.getService().edit(payment);
        log.info("Edit Payment");
        return payment;
    }

    public Payment remove(Integer id) throws Exception {
        Payment payment=PaymentService.getService().findById(id);
        PaymentService.getService().remove(id);
        log.info("Remove Payment");
        return payment;
    }

    public List<Payment> findAll() throws Exception {
        log.info("FindAll Payment");
        return PaymentService.getService().findAll();
    }

    public List<Payment> findByAll(String searchText) throws Exception {
        log.info("FindByAll Payment");
        return PaymentService.getService().findByAll(searchText);
    }

    public Payment findById(Integer id) throws Exception {
        log.info("FindById Payment");
        return PaymentService.getService().findById(id);
    }

    public List<Payment> findByCustomerId(int customerId) throws Exception {
        log.info("FindByCustomerId Payment");
        return PaymentService.getService().findByCustomerId(customerId);
    }
}


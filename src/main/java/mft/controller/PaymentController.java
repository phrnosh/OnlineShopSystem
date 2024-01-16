package mft.controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Payment;
import mft.model.entity.enums.PaymentType;
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

    public Payment save(Double totalcost, String paymentdetails, String type, LocalDateTime paymentdate) throws Exception {
        Payment payment =
                Payment
                        .builder()
                        .totalCost(totalcost)
                        .PaymentDetails(paymentdetails)
                        .PaymentType(type)
                        .PaymentTimeStamp(paymentdate)
                        .build();
        PaymentService.getService().save(payment);
        log.info("Save");
        return payment;
    }

    public Payment edit(Integer id, Double totalcost, String paymentdetails, String type, LocalDateTime paymentdate) throws Exception {
        Payment payment =
                Payment
                        .builder()
                        .id(id)
                        .totalCost(totalcost)
                        .PaymentDetails(paymentdetails)
                        .PaymentType(type)
                        .PaymentTimeStamp(paymentdate)
                        .build();
        PaymentService.getService().edit(payment);
        return payment;
    }

    public Payment remove(Integer id) throws Exception {
        Payment payment=PaymentService.getService().findById(id);
        PaymentService.getService().remove(id);
        return payment;
    }

    public List<Payment> findAll() throws Exception {
        return PaymentService.getService().findAll();
    }

//    public List<OrderDetails> findByAll(String searchText) throws Exception {
//        return OrderDetailsService.getService().findByAll(searchText);
//    }

    public Payment findById(Integer id) throws Exception {
        return PaymentService.getService().findById(id);
    }
}


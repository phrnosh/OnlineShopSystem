package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.OrderDetails;
import mft.model.entity.Payment;
import mft.model.repository.OrderDetailsRepository;
import mft.model.repository.PaymentRepository;

import java.util.List;
@Log4j
public class PaymentService {

    private static PaymentService service = new PaymentService();

    private PaymentService() {
    }

    public static PaymentService getService() {
        return service;
    }

    public Payment save(Payment payment) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.save(payment);
        }
    }

    public Payment edit(Payment payment) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.edit(payment);
        }
    }

    public Payment remove(int id) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            Payment payment = paymentRepository.findById(id);
            if (payment != null) {
                paymentRepository.remove(id);
                return payment;
            } else {
                return null;
            }
        }
    }

    public List<Payment> findAll() throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findAll();
        }
    }

    public List<Payment> findByAll(String searchText) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findByAll(searchText);
        }
    }

    public Payment findById(int id) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findById(id);
        }
    }

    public List<Payment> findByCustomerId(int customerId) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findByCustomerId(customerId);
        }
    }
}

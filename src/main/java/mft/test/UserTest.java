package mft.test;

import lombok.extern.log4j.Log4j;
import mft.controller.*;
import mft.model.entity.Customer;
import mft.model.entity.OrderDetails;
import mft.model.entity.Payment;
import mft.model.entity.Products;
import mft.model.entity.enums.PaymentType;

import java.time.LocalDateTime;

@Log4j
public class UserTest {
    public static void main(String[] args) throws Exception {
        log.info("Main");

//        System.out.println(CustomerController.getController().save(
//                "zahra",
//                "ahmadi",
//                "zaahra",
//                "12369741",
//                "khaiadhdabcjsbcj",
//                "09148184037",
//                "gadgions@gmail.com",
//                true));
//
//        System.out.println(CustomerController.getController().edit(
//                1,
//                "ali",
//                "alipour139765",
//                false));
//
//        System.out.println(CustomerController.getController().remove(2));
//
//        System.out.println(CustomerController.getController().findAll());
//
//        System.out.println(CustomerController.getController().findById(2));
//
//        System.out.println(CustomerController.getController().findByUsername("zahra"));

//
//
//        System.out.println(ProductController.getController().save(
//                "goshi iphone13promax",
//                "apple",
//                "8 inch",
//                50000000.0,
//                "گوشی عالی با کیفیت دوربین فوق العاده"));
//
//       System.out.println(OrderDetailsController.getController().save(
//                 new Products(),
//                2));

//        System.out.println(PaymentController.getController().save(
//                100009000.0,
//                "ممنون از خرید شما",
//                PaymentType.OnlinePayment,
//                LocalDateTime.now()));

//        System.out.println(OrderController.getController().save(
//                new Customer(),
//                new OrderDetails(),
//                new Payment(),
//                100009000.0,
//                200.000F,
//                LocalDateTime.now())
//                );
    }
}

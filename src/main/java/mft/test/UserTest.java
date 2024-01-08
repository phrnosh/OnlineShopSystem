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
//                "ali",
//                "alipour",
//                "fdfsgs",
//                "12369741",
//                "khaiadhdabcjsbcj",
//                "09866818437",
//                "gadgions@gmail.com",
//                true));

//        System.out.println(CustomerController.getController().edit(
//                1,
//                "ali",
//                "alipour",
//                "fdfsgs",
//                "12369741",
//                "khaiadhdabcjsbcj",
//                "09866818437",
//                "gadgions@gmail.com",
//                false));
//
//        System.out.println(ProductController.getController().remove(10));
//
//        System.out.println(ProductController.getController().findByAll("bag"));
//
//        System.out.println(CustomerController.getController().findById(2));
//
//        System.out.println(CustomerController.getController().findByUsernameAndPassword("admin" , "admin"));

//
//
//        System.out.println(ProductController.getController().save(
//                "bag",
//                "zara",
//                "20cm",
//                200000.0,
//                "جنس خوب"));

//       System.out.println(OrderDetailsController.getController().save(
//                 ProductController.findByName(""),
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

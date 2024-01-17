package mft.test;

import lombok.extern.log4j.Log4j;
import mft.controller.OrderController;
import mft.controller.PaymentController;

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
//                "ali",
//                "alipour",
//                "fdfsgs",
//                "123456789",
//                "اصفهان ...",
//                "09126479515",
//                "garftbjnks@gmail.com",
//                false));
////
//        System.out.println(ProductController.getController().remove(10));
//
//        System.out.println(ProductController.getController().findByAll("bag"));
//
//        System.out.println(CustomerController.getController().findById(2));

//        System.out.println(OrderDetailsController.getController().findByCustomerId(42));
//
//        System.out.println(OrderController.getController().findByCustomerId(42));

        System.out.println(PaymentController.getController().findAll());

//        System.out.println(OrderController.getController().findAll());

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
//                 ProductController.getController().findByName("aaa"),
//                2));

//               System.out.println(OrderDetailsController.getController().save(
//                42,
//                1,
//                21,
//                2,
//                       200000.0));

//        System.out.println(OrderController.getController().save(
//                41,
//                500000000.0,
//                200000.0F,
//                null,
//                LocalDateTime.now()));

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

package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import mft.controller.CustomerController;
import mft.model.entity.Customer;

//@Log4j
public class Main extends Application {
//    public static void main(String[] args) throws Exception {
//        log.info("Main");
//        Customer customer = new Customer();
//        customer.setName("ahmad");
//        customer.setFamily("messbah");
//        CustomerController.getController().save(customer.getName(), customer.getFamily(), customer.getUsername(), customer.getPassword(), customer.getAddress(), customer.getPhoneNumber(), customer.getEmail(),customer.isStatus());
////        List<Person> personList = PersonService.getService().findAll();
//    }
@Override
public void start(Stage primaryStage) throws Exception {
    Scene scene = new Scene(
            FXMLLoader.load(getClass().getClassLoader().getResource("../mft/view/signinFrame.fxml"))
    );
    primaryStage.setScene(scene);
    primaryStage.setTitle("Register Information");
    primaryStage.show();

}

    public static void main(String[] args) {
        launch(args);
    }
}
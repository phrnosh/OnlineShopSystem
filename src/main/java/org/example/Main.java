package org.example;

import lombok.extern.log4j.Log4j;
import mft.controller.CustomerController;
import mft.model.entity.Customer;

@Log4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.info("Main");
        Customer customer = new Customer();
        customer.setName("ahmad");
        customer.setFamily("messbah");
        CustomerController.getController().save(customer.getName(), customer.getFamily(), customer.getUsername(), customer.getPassword(), customer.getAddress(), customer.getPhoneNumber(), customer.getEmail(),customer.isStatus());
//        List<Person> personList = PersonService.getService().findAll();
    }
}
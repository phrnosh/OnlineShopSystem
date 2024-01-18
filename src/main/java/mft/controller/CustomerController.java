package mft.controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.service.CustomerService;

import java.util.List;
import java.util.regex.Pattern;

@Log4j
public class CustomerController {
    private static CustomerController controller=new CustomerController();

    private CustomerController() {
    }

    public static CustomerController getController() {
        return controller;
    }

    public Customer save(String name, String family, String username, String password , String address, String phoneNumber, String email, Boolean status) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$" ,name) &&
                (Pattern.matches("^[a-zA-Z\\s]{3,30}$" ,family)) &&
                (Pattern.matches("^[a-z\\d\\S\\._]{3,30}$" ,username)) &&
                (Pattern.matches("^[\\w\\S]{3,30}$" , password)) &&
                (Pattern.matches("^(\\+989|09)\\d{6,9}$" , phoneNumber)) &&
                (Pattern.matches("^[a-zA-Z\\d\\._]+@(gmail|yahoo)\\.com$" , email))) {
            Customer customer =
                    Customer
                            .builder()
                            .name(name)
                            .family(family)
                            .username(username)
                            .password(password)
                            .address(address)
                            .phoneNumber(phoneNumber)
                            .email(email)
                            .status(status)
                            .build();
            CustomerService.getService().save(customer);
            log.info("Save Customer");
            return customer;
        }else {
            throw new Exception("Invalid Data");
        }
    }

    public Customer edit(Integer id, String name, String family, String username, String password , String address, String phoneNumber, String email, Boolean status) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$" ,name) &&
                (Pattern.matches("^[a-zA-Z\\s]{3,30}$" ,family)) &&
                (Pattern.matches("^[a-z\\d\\S\\._]{3,30}$" ,username)) &&
                (Pattern.matches("^[\\w\\S]{3,30}$" , password)) &&
                (Pattern.matches("^(\\+989|09)\\d{6,9}$" , phoneNumber)) &&
                (Pattern.matches("^[a-zA-Z\\d\\._]+@(gmail|yahoo)\\.com$" , email))) {
            Customer customer =
                    Customer
                            .builder()
                            .id(id)
                            .name(name)
                            .family(family)
                            .username(username)
                            .password(password)
                            .address(address)
                            .phoneNumber(phoneNumber)
                            .email(email)
                            .status(status)
                            .build();
            CustomerService.getService().edit(customer);
            log.info("Edit Customer");
            return customer;
        }else {
            throw new Exception("Invalid Data");
        }
    }

    public Customer remove(Integer id) throws Exception {
        Customer customer=CustomerService.getService().findById(id);
        CustomerService.getService().remove(id);
        log.info("Remove Customer");
        return customer;
    }

    public List<Customer> findAll() throws Exception {
        log.info("FindAll Customer");
        return CustomerService.getService().findAll();
    }

    public List<Customer> findByAll(String searchText) throws Exception {
        log.info("FindByAll Customer");
        return CustomerService.getService().findByAll(searchText);
    }

    public Customer findById(Integer id) throws Exception {
        log.info("FindById Customer");
        return CustomerService.getService().findById(id);
    }

    public Customer findByUsername(String username) throws Exception {
        log.info("FindByUsername Customer");
        return CustomerService.getService().findByUsername(username);

    }

    public Customer findByUsernameAndPassword(String username, String password) throws Exception {
        Customer customer = CustomerService.getService().findByUsernameAndPassword(username, password);
        if (customer != null) {
            log.info("FindByUsernameAndPassword Customer");
            return customer;
        }
        throw new Exception("Invalid Username/Password");
    }
}

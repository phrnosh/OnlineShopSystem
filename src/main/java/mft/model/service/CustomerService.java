package mft.model.service;

import mft.model.entity.Customer;
import mft.model.repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private static CustomerService service = new CustomerService();

    private CustomerService() {
    }

    public static CustomerService getService() {
        return service;
    }

    public Customer save(Customer customer) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            if (customerRepository.findByUsername(customer.getUsername()) == null) {
                return customerRepository.save(customer);
            } else {
                throw new Exception();
            }
        }
    }

    public Customer edit(Customer customer) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.edit(customer);
        }
    }

    public Customer remove(int id) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            Customer customer = customerRepository.findById(id);
            if (customer != null) {
                customerRepository.remove(id);
                return customer;
            } else {
                return null;
            }
        }
    }

    public List<Customer> findAll() throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findAll();
        }
    }

    public List<Customer> findByAll(String searchText) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findByAll(searchText);
        }
    }

    public Customer findById(int id) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findById(id);
        }
    }

    public Customer findByUsername(String username) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findByUsername(username);
        }
    }

    public Customer findByUsernameAndPassword(String username, String password) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findByUsername(username);
        }
    }
}

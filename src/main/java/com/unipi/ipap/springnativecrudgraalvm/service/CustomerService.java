package com.unipi.ipap.springnativecrudgraalvm.service;

import com.unipi.ipap.springnativecrudgraalvm.entity.Customer;
import com.unipi.ipap.springnativecrudgraalvm.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}

package com.unipi.ipap.springnativecrudgraalvm.service;

import com.unipi.ipap.springnativecrudgraalvm.entity.Customer;
import com.unipi.ipap.springnativecrudgraalvm.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> addCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers).stream()
                .toList();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer modifyCustomer(Long id, Customer customer) {
        return customerRepository.findById(id).map(cmr -> {
            cmr.setName(customer.getName());
            cmr.setAddressList(customer.getAddressList());
            cmr.setAge(customer.getAge());
            cmr.setEmail(customer.getEmail());
            cmr.setPhone(customer.getPhone());
            return customerRepository.save(cmr);
        })
        .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}

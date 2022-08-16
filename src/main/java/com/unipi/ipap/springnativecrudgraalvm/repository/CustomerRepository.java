package com.unipi.ipap.springnativecrudgraalvm.repository;

import com.unipi.ipap.springnativecrudgraalvm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

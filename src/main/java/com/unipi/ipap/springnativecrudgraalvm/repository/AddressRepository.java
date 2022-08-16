package com.unipi.ipap.springnativecrudgraalvm.repository;

import com.unipi.ipap.springnativecrudgraalvm.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

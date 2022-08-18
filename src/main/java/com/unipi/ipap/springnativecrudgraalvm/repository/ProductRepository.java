package com.unipi.ipap.springnativecrudgraalvm.repository;

import com.unipi.ipap.springnativecrudgraalvm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}

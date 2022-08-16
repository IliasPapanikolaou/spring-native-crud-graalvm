package com.unipi.ipap.springnativecrudgraalvm.repository;

import com.unipi.ipap.springnativecrudgraalvm.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}

package com.unipi.ipap.springnativecrudgraalvm.repository;

import com.unipi.ipap.springnativecrudgraalvm.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Modifying
    @Query("update Item i set i.available=?1 where i.id=?2")
    int updateItemAvailabilityById(Boolean bool, UUID itemId);
}

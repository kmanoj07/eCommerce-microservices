package com.kumarmanoj.invenetoryservice.repository;

import com.kumarmanoj.invenetoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // Spring data JPA will generate the SQL query determining the skuCode as the attribute of Inventory
    //Optional<Inventory> findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}

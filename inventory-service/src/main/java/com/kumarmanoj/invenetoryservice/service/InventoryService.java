package com.kumarmanoj.invenetoryservice.service;

import com.kumarmanoj.invenetoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly=true)
    public boolean isInStock(String skuCode) {
        // find into Inventory with skuCode
        // extension method for spring data JPA
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}

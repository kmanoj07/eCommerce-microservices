package com.kumarmanoj.invenetoryservice.service;

import com.kumarmanoj.invenetoryservice.dto.InventoryResponse;
import com.kumarmanoj.invenetoryservice.model.Inventory;
import com.kumarmanoj.invenetoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly=true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        // find into Inventory with skuCode
        // extension method for spring data JPA
        //return inventoryRepository.findBySkuCode(skuCode).isPresent();
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> mapToInventoryResponsey(inventory)).toList();
    }

    public InventoryResponse mapToInventoryResponsey(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}

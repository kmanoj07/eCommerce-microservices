package com.kumarmanoj.invenetoryservice.controller;

import com.kumarmanoj.invenetoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode) { // Here we can take the skuCode as request parameter , Path Variable (/)
        return inventoryService.isInStock(skuCode);
    }
}

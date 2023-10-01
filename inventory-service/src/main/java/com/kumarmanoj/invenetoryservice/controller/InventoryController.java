package com.kumarmanoj.invenetoryservice.controller;

import com.kumarmanoj.invenetoryservice.dto.InventoryResponse;
import com.kumarmanoj.invenetoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    //Request as Path Variable
    // http://localhost:8083/api/inventory/sku-code
    // Multiple sku-code in request
    // http://localhost:8083/api/inventory/sku-code, sku-code, sku-code

    //@GetMapping("/{sku-code}")
    //@ResponseStatus(HttpStatus.OK)
    //public boolean isInStock(@PathVariable("sku-code") String skuCode) { // Here we can take the skuCode as request parameter , Path Variable (/)
    //    return inventoryService.isInStock(skuCode);
    //}

    // More readable way is Instead of going with Path variable use Request parameter
    // http://localhost:8083/api/inventory?sku-code=iphone_13&sku-code=iphone_13_red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) { // Here we can take the skuCode as request parameter , Path Variable (/)
        return inventoryService.isInStock(skuCode);
    }
}

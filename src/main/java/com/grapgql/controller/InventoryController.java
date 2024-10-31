package com.grapgql.controller;

import com.grapgql.dto.Product;
import com.grapgql.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @GetMapping("/get-all-products")
    public List<Product> viewProducts() {
        return inventoryService.viewProducts();
    }

    @GetMapping("/get-product-byId/{pId}")
    public Product getProductById(@PathVariable int pId) {
        return inventoryService.getProductById(pId);
    }

    @PostMapping("/create-product")
    public String createProduct(@RequestBody Product product) {
        return inventoryService.createProduct(product);
    }

    @PutMapping("update-product")
    public String updateProduct(@RequestBody Product product) {
        return inventoryService.updateProduct(product);
    }

}

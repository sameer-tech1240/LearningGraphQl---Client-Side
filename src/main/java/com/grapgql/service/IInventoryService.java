package com.grapgql.service;

import com.grapgql.dto.Product;

import java.util.List;

public interface IInventoryService {
    List<Product> viewProducts();

    Product getProductById(int pId);
}

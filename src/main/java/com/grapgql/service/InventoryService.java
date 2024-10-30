package com.grapgql.service;

import com.grapgql.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService implements IInventoryService {

    @Autowired
    private GraphQlClient graphQlClient;

    @Override
    public List<Product> viewProducts() {
        String getAllQuery = """
                query MyQuery {
                  getAllProducts {
                    id
                    price
                    productName
                  }
                }""";
        return graphQlClient.document(getAllQuery)
                .retrieve("getAllProducts")
                .toEntityList(Product.class).block();
    }

    @Override
    public Product getProductById(int pId) {
        String getProductByIdQuery = String.format("""
                query MyQuery {
                  getProductById(id: %d) {
                    price
                    productName
                  }
                }""", pId);
        return graphQlClient.document(getProductByIdQuery)
                .retrieve("getProductById")
                .toEntity(Product.class).block();
    }
}

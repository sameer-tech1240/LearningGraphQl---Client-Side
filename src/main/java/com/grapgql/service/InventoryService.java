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

    @Override
    public String createProduct(Product product) {
        String createProductQuery = String.format("""
                mutation MyMutation {
                  createProduct(productDto: { price: %f, productName: "%s", productQuantity: %d }) {
                    id
                    price
                    productName
                    productQuantity
                  }
                }""" , product.price(), product.productName(), product.productQuantity());
        Product response = graphQlClient.document(createProductQuery).retrieve("createProduct").toEntity(Product.class)
                .block();

        if (response == null) {
            return "Product creation failed";
        }
        return "Product created successfully with id : " + response.id();
    }

    @Override
    public String updateProduct(Product product) {
        String updateProductQuery = String.format("""
                mutation MyMutation {
                  updateProductQuantity(id: %d, productQuantity: %d) {
                    id
                    price
                    productName
                    productQuantity
                  }
                }""" , product.id(), product.productQuantity());
        Product response = graphQlClient.document(updateProductQuery).retrieve("updateProductQuantity").toEntity(Product.class)
                .block();
        assert response != null;
        if(response.id() == 0 || response.productQuantity()==0)
            return "Product update failed";
        return "Product updated successfully with id : " + response.id();
    }


}

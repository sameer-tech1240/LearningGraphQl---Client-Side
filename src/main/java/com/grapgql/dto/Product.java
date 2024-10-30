package com.grapgql.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record Product(int id , String productName, float price, int productQuantity) {
}

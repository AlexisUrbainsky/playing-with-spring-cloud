package com.alexis.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long productID;
    private String productName;
    private String productDesc;
    private Integer productPrice;
    private Boolean productStock;

}

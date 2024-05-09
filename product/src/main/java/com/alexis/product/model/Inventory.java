package com.alexis.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private Long inventoryID;
    private Long productID;
    private Boolean inStock;
}

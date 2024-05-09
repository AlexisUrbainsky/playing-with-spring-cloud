package com.alexis.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Inventory {
    private Long inventoryID;
    private Long productID;
    private Boolean inStock;
}

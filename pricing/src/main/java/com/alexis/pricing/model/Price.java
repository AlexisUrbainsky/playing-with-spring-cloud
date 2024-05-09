package com.alexis.pricing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Price {
    private Long priceID;
    private Long productID;
    private Integer originalPrice;
    private Integer discountedPrice;
}

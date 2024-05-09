package com.alexis.pricing.controller;

import com.alexis.pricing.model.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/price")
@RestController
public class PriceController {

    public PriceController() {
        populatepriceList();
    }

    private List<Price> priceList = new ArrayList<Price>();

    Logger logger = LoggerFactory.getLogger(PriceController.class);

    @GetMapping("/{productId}")
    public Price getPriceDetails(@PathVariable long productId){
        logger.info("Price Details");

        return getPriceInfo(productId);
    }

    private Price getPriceInfo(Long productId) {
        for (Price p : priceList) {
            if (productId.equals(p.getProductID())) {
                return p;
            }
        }
        return null;
    }

    private void populatepriceList() {
        priceList.clear();
        priceList.add(new Price(201L, 101l, 1999, 999));
        priceList.add(new Price(202L, 102l, 199, 19));
        priceList.add(new Price(203L, 103l, 1222, 600));
    }
}

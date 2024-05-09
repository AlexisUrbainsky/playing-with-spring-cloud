package com.alexis.inventory.controller;

import com.alexis.inventory.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/inventory")
@RestController
public class InventoryController {

    private Logger logger = LoggerFactory.getLogger(InventoryController.class);

    public InventoryController() {
        populateInventoryList();
    }

    List<Inventory> inventoryList = new ArrayList<Inventory>();

    @GetMapping("/{productId}")
    public Inventory getInventoryDetails(@PathVariable Long productId) {
        logger.info("get inventory details");
        return getInventoryInfo(productId);
    }

    private Inventory getInventoryInfo(Long productId) {

        for (Inventory i : inventoryList) {
            if (productId.equals(i.getProductID())) {
                return i;
            }
        }

        return null;
    }

    private void populateInventoryList() {
        inventoryList.clear();
        inventoryList.add(new Inventory(301L, 101l, true));
        inventoryList.add(new Inventory(302L, 102l, true));
        inventoryList.add(new Inventory(303L, 103l, false));
    }


}

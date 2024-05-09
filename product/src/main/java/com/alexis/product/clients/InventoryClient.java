package com.alexis.product.clients;

import com.alexis.product.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory")
public interface InventoryClient {

    @GetMapping("/inventory/{productId}")
    Inventory getInventoryDetails(@PathVariable("productId") long productId);

}

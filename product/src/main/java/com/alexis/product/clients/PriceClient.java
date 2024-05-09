package com.alexis.product.clients;

import com.alexis.product.model.Price;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gateway")
public interface PriceClient {

    @GetMapping("/price/{productId}")
    Price getPriceDetails(@PathVariable("productId") long productId);

}

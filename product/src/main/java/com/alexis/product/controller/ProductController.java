package com.alexis.product.controller;

import com.alexis.product.model.Inventory;
import com.alexis.product.model.Price;
import com.alexis.product.model.Product;
import com.alexis.product.model.ProductInfo;
import com.alexis.product.clients.InventoryClient;
import com.alexis.product.clients.PriceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    public ProductController() {
        populateProductList();
    }

    @Autowired
    PriceClient priceClient;

    @Autowired
    InventoryClient inventoryClient;

    List<ProductInfo> productList = new ArrayList<ProductInfo>();

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${resilience4j.circuitbreaker.instances.product.registerHealthIndicator}")
    private String var;

    @Value("${arre}")
    private String loco;

    @CircuitBreaker(name="product", fallbackMethod = "getProductDetail")
    @Retry(name="product", fallbackMethod = "getProductDetail")
    @GetMapping("/{productId}")
    public Product getProductDetails(@PathVariable Long productId) {

        logger.info("Product Details");

        logger.info("resilience4j.circuitbreaker.instances.product.registerHealthIndicator " + var);

        // Get Name and Desc from produ1t-service
        ProductInfo productInfo = getProductInfo(productId);

        // Get Price from pricing-service
        Price price = priceClient.getPriceDetails(productId);

        // Get Stock Available from inventory-service
        Inventory inventory = inventoryClient.getInventoryDetails(productId);

        return new Product(productInfo.getProductID(), productInfo.getProductName(), productInfo.getProductDesc(),
                price.getDiscountedPrice(), inventory.getInStock());
    }

    public Product getProductDetail(Exception e){
        logger.info("Circuit Breaker, Error calling Price Service " + e.getMessage());
        return new Product();
    }

    private ProductInfo getProductInfo(Long productid) {

        for (ProductInfo p : productList) {
            if (productid.equals(p.getProductID())) {
                return p;
            }
        }

        return null;
    }

    private void populateProductList() {
        productList.clear();
        productList.add(new ProductInfo(101L, "iPhone", "iPhone is damn expensive!"));
        productList.add(new ProductInfo(102L, "Book", "Book is great!"));
        productList.add(new ProductInfo(103L, "Washing MC", "Washing MC is necessary"));
    }
}

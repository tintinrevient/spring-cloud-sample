package com.hncy.demo.dataservice.client;

import com.hncy.demo.dataservice.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping(value = "/product")
    List<Product> getAllProducts();

    @GetMapping(value = "/product/{id}")
    Product getProductById(@PathVariable("id") Long id);

}

package com.hncy.demo.productservice.controller;

import com.hncy.demo.productservice.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import com.hncy.demo.productservice.domain.Product;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        logger.info("product service is called.");
        return productRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable("id") Long id) {
        logger.info("product service is called.");
        return productRepository.findById(id).get();
    }
}
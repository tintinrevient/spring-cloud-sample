package com.hncy.demo.dataservice.controller;

import com.hncy.demo.dataservice.client.ProductClient;
import com.hncy.demo.dataservice.client.ReviewClient;
import com.hncy.demo.dataservice.client.UserClient;
import com.hncy.demo.dataservice.domain.Product;
import com.hncy.demo.dataservice.domain.Review;
import com.hncy.demo.dataservice.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/data")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ReviewClient reviewClient;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @GetMapping(value = "/aggregate/review/{id}")
    @HystrixCommand(fallbackMethod = "aggregateByDefault", threadPoolKey = "aggregateThreadPool")
    public Map<String, Object> aggregate(@PathVariable("id") Long id) {
        logger.info("data service is called.");

        Review review = reviewClient.getReviewById(id);

        System.out.println(review.toString());

        Long productId = review.getProduct();
        Long userId = review.getUser();

        Product product = productClient.getProductById(productId);
        User user = userClient.getUserById(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("review", review);
        map.put("product", product);
        map.put("user", user);

        return map;
    }

    public Map<String, Object> aggregateByDefault(Long id) {
        return new HashMap<>();
    }


    @GetMapping(value = "/product")
    @HystrixCommand(fallbackMethod = "getAllProductsByDefault", threadPoolKey = "productThreadPool")
    public List<Product> getAllProducts() {
        logger.info("data service is called.");
        return productClient.getAllProducts();
    }

    @GetMapping(value = "/product/{id}")
    @HystrixCommand(fallbackMethod = "getProductByIdByDefault", threadPoolKey = "productThreadPool")
    public Product getProductById(@PathVariable("id") Long id) {
        logger.info("data service is called.");
        return productClient.getProductById(id);
    }

    public List<Product> getAllProductsByDefault() {
        logger.info("data service is called.");
        return new ArrayList<Product>();
    }

    public Product getProductByIdByDefault(Long id) {
        logger.info("data service is called.");
        return new Product();
    }

    @RequestMapping(value = "/user")
    @HystrixCommand(fallbackMethod = "getAllUsersByDefault", threadPoolKey = "userThreadPool")
    public List<User> getAllUsers() {
        logger.info("data service is called.");
        return userClient.getAllUsers();
    }

    @RequestMapping(value = "/user/{id}")
    @HystrixCommand(fallbackMethod = "getUserByIdByDefault", threadPoolKey = "userThreadPool")
    public User getUserById(@PathVariable("id") Long id) {
        logger.info("data service is called.");
        return userClient.getUserById(id);
    }

    public List<User> getAllUsersByDefault() {
        logger.info("data service is called.");
        return new ArrayList<User>();
    }

    public User getUserByIdByDefault(Long id) {
        logger.info("data service is called.");
        return new User();
    }

    @GetMapping(value = "/review")
    @HystrixCommand(fallbackMethod = "getAllReviewsByDefault", threadPoolKey = "reviewThreadPool")
    public List<Review> getAllReviews() {
        logger.info("data service is called.");

        ResponseEntity<Review[]> responseEntity = restTemplate.getForEntity("http://review-service/review", Review[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @GetMapping(value = "/review/{id}")
    @HystrixCommand(fallbackMethod = "getReviewByIdByDefault", threadPoolKey = "reviewThreadPool")
    public Review getReviewById(@PathVariable("id") Long id) {
        logger.info("data service is called.");

        ResponseEntity<Review> responseEntity = restTemplate.getForEntity("http://review-service/review/{id}", Review.class, id);
        return responseEntity.getBody();
    }

    public List<Review> getAllReviewsByDefault() {
        logger.info("data service fallback is called.");
        return new ArrayList<Review>();
    }

    public Review getReviewByIdByDefault(Long id) {
        logger.info("data service fallback is called.");
        return new Review();
    }

}

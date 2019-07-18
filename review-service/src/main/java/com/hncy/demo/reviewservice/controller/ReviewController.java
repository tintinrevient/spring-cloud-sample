package com.hncy.demo.reviewservice.controller;

import com.hncy.demo.reviewservice.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hncy.demo.reviewservice.domain.Review;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping
    public List<Review> findAll() {
        logger.info("review service is called.");
        return reviewRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Review findById(@PathVariable("id") Long id) {
        logger.info("review service is called.");
        return reviewRepository.findById(id).get();
    }
}
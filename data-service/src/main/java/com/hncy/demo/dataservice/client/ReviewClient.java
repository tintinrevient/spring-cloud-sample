package com.hncy.demo.dataservice.client;

import com.hncy.demo.dataservice.domain.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient("review-service")
public interface ReviewClient {

    @GetMapping(value = "/review")
    List<Review> getAllReviews();

    @GetMapping(value = "/review/{id}")
    Review getReviewById(@PathVariable("id") Long id);

}

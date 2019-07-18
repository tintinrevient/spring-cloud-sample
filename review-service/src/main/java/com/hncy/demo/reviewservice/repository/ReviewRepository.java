package com.hncy.demo.reviewservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hncy.demo.reviewservice.domain.Review;

public interface ReviewRepository  extends JpaRepository<Review, Long> {

}

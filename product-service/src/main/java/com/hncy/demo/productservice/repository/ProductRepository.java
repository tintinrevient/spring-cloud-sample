package com.hncy.demo.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hncy.demo.productservice.domain.Product;

public interface ProductRepository  extends JpaRepository<Product, Long> {

}

package com.hncy.demo.userservice.repository;

import com.hncy.demo.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

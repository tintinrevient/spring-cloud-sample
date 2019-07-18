package com.hncy.demo.dataservice.client;

import com.hncy.demo.dataservice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping(value = "/user")
    List<User> getAllUsers();

    @GetMapping(value = "/user/{id}")
    User getUserById(@PathVariable("id") Long id);

}

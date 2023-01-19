package com.shop.myshop.repository;

import com.shop.myshop.dto.CustomerDto;
import com.shop.myshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}

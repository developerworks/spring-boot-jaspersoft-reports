package com.example.demo7springbootjaspersoftreports.repository;

import com.example.demo7springbootjaspersoftreports.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCreatedAt(LocalDate localDate);
}

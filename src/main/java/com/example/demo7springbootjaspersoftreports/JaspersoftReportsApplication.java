package com.example.demo7springbootjaspersoftreports;

import com.example.demo7springbootjaspersoftreports.entity.Product;
import com.example.demo7springbootjaspersoftreports.entity.ProductType;
import com.example.demo7springbootjaspersoftreports.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class JaspersoftReportsApplication implements CommandLineRunner {

    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(JaspersoftReportsApplication.class, args);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = List.of(
                new Product("Samsung galaxy", "4GB RAM",
                        ProductType.PHONE, new BigDecimal("300"), LocalDate.now()),
                new Product("Techno Spark", "2GB RAM",
                        ProductType.PHONE, new BigDecimal("500"), LocalDate.now()),
                new Product("HP parvillion", "250GB SSD",
                        ProductType.COMPUTER, new BigDecimal("600"), LocalDate.now().minusDays(1)),
                new Product("Dell", "DDR4 hard disk",
                        ProductType.COMPUTER, new BigDecimal("700"), LocalDate.now().minusDays(1)),
                new Product("Acer", "4GB RAM",
                        ProductType.COMPUTER, new BigDecimal("200"), LocalDate.now().minusDays(1)),
                new Product("Huawei", "high resolution camera",
                        ProductType.PHONE, new BigDecimal("400"), LocalDate.now().minusDays(1))
        );
        productRepository.saveAll(products);
    }
}

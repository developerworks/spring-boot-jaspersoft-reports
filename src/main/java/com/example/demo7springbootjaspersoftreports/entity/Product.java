package com.example.demo7springbootjaspersoftreports.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@org.hibernate.annotations.Table(appliesTo = "product", comment = "产品信息")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", unique = true, nullable = false, columnDefinition = "bigint(20) COMMENT '主键'")
    private Long id;

    @NonNull
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '产品名称'")
    private String name;

    @NonNull
    @Column(name = "description", columnDefinition = "varchar(255) COMMENT '产品描述'")
    private String description;

    @NonNull
    @Column(name = "product_type", columnDefinition = "varchar(255) COMMENT '产品类型'")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @NonNull
    @Column(name = "price", columnDefinition = "decimal(38,2) COMMENT '价格'")
    private BigDecimal price;

    @NonNull
    @Column(name = "created_at", columnDefinition = "date COMMENT '创建时间'")
    private LocalDate createdAt;

}
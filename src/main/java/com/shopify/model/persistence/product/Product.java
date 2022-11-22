package com.shopify.model.persistence.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @Column(nullable = false)
    private double price;

    @NotNull
    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String image;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private long createDate;

    @NotNull
    @Column(name = "modify_date", nullable = false)
    private long modifyDate = 0L;
}

package com.shopify.model.persistence.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Category category;

    @NotNull
    @Column(name = "CREATE_DATE", nullable = false)
    private long createDate;

    @NotNull
    @Column(name = "MODIFY_DATE", nullable = false)
    private long modifyDate = 0L;
}

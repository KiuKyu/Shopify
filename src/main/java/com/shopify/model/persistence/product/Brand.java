package com.shopify.model.persistence.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String description;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private long createDate;

    @NotNull
    @Column(name = "modify_date", nullable = false)
    private long modifyDate = 0L;
}

package com.shopify.model.dto;

import com.shopify.model.persistence.product.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Brand brand;
    private long createDate;
    private long modifyDate;
}

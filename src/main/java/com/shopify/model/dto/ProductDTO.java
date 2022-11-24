package com.shopify.model.dto;

import com.shopify.model.persistence.product.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Category category;
    private MultipartFile image;
    private double price;
    private long createDate;
    private long modifyDate;
}

package com.shopify.repository;

import com.shopify.model.persistence.product.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBrandRepository extends PagingAndSortingRepository<Brand, Long> {
}

package com.shopify.repository;

import com.shopify.model.persistence.user.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRoleRepository extends PagingAndSortingRepository<Role, Long> {
}

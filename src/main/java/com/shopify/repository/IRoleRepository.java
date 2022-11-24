package com.shopify.repository;

import com.shopify.model.persistence.user.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IRoleRepository extends PagingAndSortingRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

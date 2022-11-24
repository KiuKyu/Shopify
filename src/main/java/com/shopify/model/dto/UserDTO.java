package com.shopify.model.dto;

import com.shopify.model.persistence.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private List<Role> roles;
    private long createDate;
    private long modifyDate;
}

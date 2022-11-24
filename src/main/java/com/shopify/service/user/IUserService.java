package com.shopify.service.user;

import com.shopify.model.dto.UserDTO;
import com.shopify.model.persistence.user.User;
import com.shopify.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User testSave(UserDTO userDTO);

    User testFind(Long id);

    User testUpdate(Long id, User updatedUser);

    User findByUsername(String username);

    User saveUser(User user, String roleName);


}

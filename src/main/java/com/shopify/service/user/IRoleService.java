package com.shopify.service.user;

import com.shopify.model.dto.RoleDTO;
import com.shopify.model.persistence.user.Role;
import com.shopify.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role testSave(RoleDTO roleDTO);

    Role testFind(Long id);

    Role testUpdate(Long id, Role role);
}

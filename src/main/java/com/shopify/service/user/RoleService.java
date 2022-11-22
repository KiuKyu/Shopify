package com.shopify.service.user;

import com.shopify.model.dto.RoleDTO;
import com.shopify.model.persistence.user.Role;
import com.shopify.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role testSave(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(role.getName());
        role.setActive(roleDTO.isActive());
        long currentTime = System.currentTimeMillis();
        role.setCreateDate(currentTime);
        return roleRepository.save(role);
    }

    @Override
    public Role testFind(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (!roleOptional.isPresent()) {
            return null;
        } else {
            Role role = roleOptional.get();
            return role;
        }
    }

    @Override
    public Role testUpdate(Long id, Role role) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (!roleOptional.isPresent()) {
            return null;
        } else {
            role.setId(id);
            long currentTime = System.currentTimeMillis();
            role.setModifyDate(currentTime);
            return roleRepository.save(role);
        }
    }
}

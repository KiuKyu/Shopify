package com.shopify.controller;

import com.shopify.model.dto.RoleDTO;
import com.shopify.model.persistence.product.Product;
import com.shopify.model.persistence.user.Role;
import com.shopify.service.user.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping("/")
    public ResponseEntity<?> showList() {
        Iterable<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody RoleDTO roleDTO) {
        Role role = roleService.testSave(roleDTO);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        Role role = roleService.testFind(id);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Role role = roleService.testFind(id);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            roleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Role updatedRole) {
        Role role = roleService.testUpdate(id, updatedRole);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
    }
}

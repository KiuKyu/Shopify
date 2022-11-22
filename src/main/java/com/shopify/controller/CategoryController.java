package com.shopify.controller;

import com.shopify.model.dto.CategoryDTO;
import com.shopify.model.persistence.product.Category;
import com.shopify.service.product.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> showList() {
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CategoryDTO categoryDto) {
        Category newCategory = categoryService.testSave(categoryDto);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        Category category = categoryService.testFind(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category category = categoryService.testUpdate(id, updatedCategory);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Category category = categoryService.testFind(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            categoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

package com.shopify.controller;

import com.shopify.model.dto.BrandDTO;
import com.shopify.model.persistence.product.Brand;
import com.shopify.service.product.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/brands")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @GetMapping("/")
    public ResponseEntity<?> showList() {
        Iterable<Brand> brands = brandService.findAll();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
        Brand newBrand = brandService.testSave(brandDTO);
        return new ResponseEntity<>(newBrand, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        Brand brand = brandService.testFind(id);
        if (brand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(brand, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Brand brand = brandService.testFind(id);
        if (brand == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            brandService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Brand oldBrand) {
        Brand updatedBrand = brandService.testUpdate(id, oldBrand);
        if (updatedBrand == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
        }
    }
}

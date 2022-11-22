package com.shopify.service.product;

import com.shopify.model.dto.BrandDTO;
import com.shopify.model.persistence.product.Brand;
import com.shopify.repository.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public Iterable<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Brand testSave(BrandDTO brandDTO) {
        Brand newBrand = new Brand();
        newBrand.setName(brandDTO.getName());
        newBrand.setDescription(brandDTO.getDescription());
        long currentTime = System.currentTimeMillis();
        newBrand.setCreateDate(currentTime);
        brandRepository.save(newBrand);
        return newBrand;
    }

    @Override
    public Brand testFind(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (!brandOptional.isPresent()) {
            return null;
        } else {
            return brandOptional.get();
        }
    }

    @Override
    public Brand testUpdate(Long id, Brand brand) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (!brandOptional.isPresent()) {
            return null;
        } else {
            long currentTime = System.currentTimeMillis();
            brand.setModifyDate(currentTime);
            brand.setId(id);
            return brandRepository.save(brand);
        }
    }
}

package com.shopify.service.product;

import com.shopify.model.dto.CategoryDTO;
import com.shopify.model.persistence.product.Category;
import com.shopify.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category testSave(CategoryDTO categoryDTO) {
        Category newCategory = new Category();
        newCategory.setName(categoryDTO.getName());
        newCategory.setBrand(categoryDTO.getBrand());

        long currentTime = System.currentTimeMillis();
        newCategory.setCreateDate(currentTime);
        categoryRepository.save(newCategory);
        return newCategory;
    }

    @Override
    public Category testFind(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return category;
        }
        return null;
    }

    @Override
    public Category testUpdate(Long id, Category updatedCategory) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            return null;
        } else {
            updatedCategory.setId(id);
            long currentTime = System.currentTimeMillis();
            updatedCategory.setModifyDate(currentTime);
            return categoryRepository.save(updatedCategory);
        }
    }
}

package com.shopify.service.product;

import com.shopify.model.dto.CategoryDTO;
import com.shopify.model.persistence.product.Category;
import com.shopify.service.IGeneralService;

public interface ICategoryService extends IGeneralService<Category> {
    Category testSave(CategoryDTO categoryDTO);

    Category testFind(Long id);

    Category testUpdate(Long id, Category category);
}

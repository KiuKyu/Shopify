package com.shopify.service.product;

import com.shopify.model.dto.ProductDTO;
import com.shopify.model.persistence.product.Product;
import com.shopify.service.IGeneralService;

public interface IProductService extends IGeneralService<Product> {
    Product testSave(ProductDTO productDTO);

    Product testFind(Long id);

    Product testUpdate(Long id, ProductDTO productDTO);
}

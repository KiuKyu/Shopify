package com.shopify.service.product;

import com.shopify.model.dto.BrandDTO;
import com.shopify.model.persistence.product.Brand;
import com.shopify.service.IGeneralService;

public interface IBrandService extends IGeneralService<Brand> {
    Brand testSave(BrandDTO brandDTO);

    Brand testFind(Long id);

    Brand testUpdate(Long id, Brand oldBrand);
}

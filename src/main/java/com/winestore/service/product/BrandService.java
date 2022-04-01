package com.winestore.service.product;

import com.winestore.domain.entity.product.Brand;
import com.winestore.service.BaseService;

import java.util.List;

public interface BrandService extends BaseService<Brand> {
    List<Brand> getAll();
}

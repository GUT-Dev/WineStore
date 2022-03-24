package com.winestore.service.product;

import com.winestore.domain.entity.product.Land;
import com.winestore.service.BaseService;

import java.util.List;

public interface LandService extends BaseService<Land> {
    List<Land> getAll();
}

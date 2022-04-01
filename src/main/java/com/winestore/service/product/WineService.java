package com.winestore.service.product;

import com.winestore.domain.entity.product.Wine;
import com.winestore.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WineService extends BaseService<Wine> {
    Wine getById(Long id);

    Page<Wine> getPage(Pageable pageable);

}

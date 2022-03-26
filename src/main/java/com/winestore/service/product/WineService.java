package com.winestore.service.product;

import com.winestore.domain.dto.WineViewDTO;
import com.winestore.domain.entity.product.Wine;
import com.winestore.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WineService extends BaseService<Wine> {
    WineViewDTO getById(Long id);

    Page<WineViewDTO> getPage(Pageable pageable);

}

package com.winestore.service.product;

import com.winestore.api.dto.filters.WineSearchFilter;
import com.winestore.domain.entity.product.Wine;
import com.winestore.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

public interface WineService extends BaseService<Wine> {
    Wine getById(Long id);

    Page<Wine> getPage(WineSearchFilter filter, Pageable pageable);

    int countRating(Long wineId);

    BigDecimal getMaxPrice();

    BigDecimal getMinPrice();

    String uploadImgForWine(Long wineId, MultipartFile file) throws IOException;
}

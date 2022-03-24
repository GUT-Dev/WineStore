package com.winestore.service.product;

import com.winestore.domain.entity.product.CustomerReview;
import com.winestore.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerReviewService extends BaseService<CustomerReview> {
    Page<CustomerReview> getPageForWine(Long wineId, Pageable pageable);
}

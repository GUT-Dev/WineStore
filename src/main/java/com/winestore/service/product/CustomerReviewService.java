package com.winestore.service.product;

import com.winestore.domain.entity.product.CustomerReview;
import com.winestore.service.BaseService;

import java.util.List;

public interface CustomerReviewService extends BaseService<CustomerReview> {
    List<CustomerReview> getForWine(Long wineId);
}

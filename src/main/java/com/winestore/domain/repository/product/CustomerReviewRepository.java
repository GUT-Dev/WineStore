package com.winestore.domain.repository.product;

import com.winestore.domain.entity.product.CustomerReview;
import com.winestore.domain.entity.product.UserWinePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReviewRepository extends CrudRepository<CustomerReview, UserWinePK> {
    Page<CustomerReview> getAllByWineId(Long wineId, Pageable pageable);
}

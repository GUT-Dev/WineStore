package com.winestore.domain.repository.product;

import com.winestore.domain.entity.product.CustomerReview;
import com.winestore.domain.entity.product.UserWinePK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerReviewRepository extends CrudRepository<CustomerReview, UserWinePK> {
    List<CustomerReview> getAllByWineId(Long wineId);
}

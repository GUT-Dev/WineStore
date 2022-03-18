package com.winestore.domain.repository.product;

import com.winestore.domain.entity.product.Land;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandRepository extends CrudRepository<Land, Long> {
}

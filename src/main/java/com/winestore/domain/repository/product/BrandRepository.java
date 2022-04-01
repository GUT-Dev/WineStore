package com.winestore.domain.repository.product;

import com.winestore.domain.entity.product.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    @Override
    List<Brand> findAll();
}

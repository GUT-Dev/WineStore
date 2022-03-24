package com.winestore.domain.repository.product;

import com.winestore.domain.entity.product.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends CrudRepository<Wine, Long> {
    Page<Wine> getPage(Pageable pageable);

    Wine getById(Long wineId);
}

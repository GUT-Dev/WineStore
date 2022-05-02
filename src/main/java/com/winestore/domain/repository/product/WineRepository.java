package com.winestore.domain.repository.product;

import com.winestore.domain.entity.product.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface WineRepository extends CrudRepository<Wine, Long> {
    Page<Wine> findAll(Specification<Wine> specification, Pageable pageable);

    @Query(value = "select round(sum(cr.rating) / count(*), 2) " +
        "from customer_review cr " +
        "where cr.wine_id = :wineId " +
        "and cr.confirm = true", nativeQuery = true)
    BigDecimal countRating(Long wineId);
}

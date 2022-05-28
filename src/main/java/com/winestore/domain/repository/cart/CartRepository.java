package com.winestore.domain.repository.cart;

import com.winestore.domain.entity.cart.Cart;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Cart getById(Long id);

    @Query(value = "select c from Cart c " +
        "where c.available = true " +
        "and c.user.id = :userId ")
    List<Cart> getAvailableCart(Long userId);

    @Query(value = "select c from Cart c " +
        "where c.available = false " +
        "and c.user.id = :userId " +
        "order by c.buyDate desc ")
    List<Cart> getOrdersHistory(Long userId);

    List<Cart> findAll(Specification<Cart> specification, Sort sort);
}

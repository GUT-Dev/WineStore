package com.winestore.domain.repository.cart;

import com.winestore.domain.entity.cart.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    @Query(value = "select c from Cart c " +
        "where c.available = true")
    Optional<Cart> getAvailableCart(Long userId);

    @Query(value = "select c from Cart c " +
        "where c.available = false")
    List<Cart> getOrdersHistory(Long userId);
}

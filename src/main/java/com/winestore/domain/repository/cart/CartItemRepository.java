package com.winestore.domain.repository.cart;

import com.winestore.domain.entity.cart.Cart;
import com.winestore.domain.entity.cart.CartItem;
import com.winestore.domain.entity.product.Wine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    CartItem getByWineAndCart(Wine wine, Cart cart);
}

package com.winestore.service.cart;

import com.winestore.domain.entity.cart.Cart;

import java.util.List;

public interface CartService {

    Cart addToCurt(Long userId, Long wineId, int amount);

    Cart changeAmount(Long cartItemId, int amount);

    Cart removeFromCart(Long cartItemId);

    void buy(Long cartId);

    Cart getCartForUser(Long userId);

    List<Cart> getHistory(Long userId);
}

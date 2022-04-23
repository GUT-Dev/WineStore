package com.winestore.service.cart;

import com.winestore.domain.entity.cart.Cart;
import com.winestore.domain.entity.cart.CartItem;

import java.util.List;
import java.util.Set;

public interface CartService {

    Cart addToCurt(Long wineId, int amount);

    Cart changeAmount(Long cartItemId, int amount);

    Cart removeFromCart(Long cartItemId);

    void buy(Long cartId);

    Set<CartItem> getCartItems();

    List<Cart> getHistory(Long userId);
}

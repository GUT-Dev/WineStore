package com.winestore.service.cart;

import com.winestore.api.dto.cart.UserCartDTO;
import com.winestore.domain.entity.cart.Cart;

import java.util.List;

public interface CartService {

    void addToCurt(Long wineId, int amount);

    Cart changeAmount(Long cartItemId, int amount);

    void removeFromCart(Long cartItemId);

    void buy();

    UserCartDTO getCart();

    List<Cart> getHistory(Long userId);
}

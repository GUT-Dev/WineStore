package com.winestore.service.cart.impl;

import com.winestore.domain.entity.cart.Cart;
import com.winestore.domain.entity.cart.CartItem;
import com.winestore.domain.entity.product.Wine;
import com.winestore.domain.repository.cart.CartItemRepository;
import com.winestore.domain.repository.cart.CartRepository;
import com.winestore.exception.OverProductAmountException;
import com.winestore.service.cart.CartService;
import com.winestore.service.product.WineService;
import com.winestore.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final WineService wineService;
    private final UserService userService;

    @Override
    @Transactional
    public Cart addToCurt(Long wineId, int amount) {
        Wine wine = wineService.getById(wineId);

        CartItem cartItem = new CartItem();
        cartItem.setWine(wine);
        cartItem.setAmount(amount);
        CartItem entity = cartItemRepository.save(cartItem);

        Cart cart = getCart(userService.getPrincipalId());
        Set<CartItem> cartItems = cart.getCartItems();
        cartItems.add(entity);
        cart.setCartItems(cartItems);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart changeAmount(Long cartItemId, int amount) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(EntityNotFoundException::new);

        cartItem.setAmount(amount);
        cartItemRepository.save(cartItem);

        return getCart(userService.getPrincipalId());
    }

    @Override
    @Transactional
    public Cart removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);

        return getCart(userService.getPrincipalId());
    }

    @Override
    @Transactional
    public void buy(Long userId) {
        Cart cart = getCart(userId);

        Set<CartItem> cartItems = cart.getCartItems();

        cartItems.forEach(item -> {
            Wine wine = item.getWine();
            int amount = wine.getAmountForSale() - wine.getSoldAmount();

            if (item.getAmount() < amount) {
                wine.setSoldAmount(wine.getSoldAmount() + item.getAmount());
            } else {
                throw new OverProductAmountException("The wine with id " + wine.getId()
                    + " has just " + amount +
                    " pcs instead of " + item.getAmount());
            }

            wineService.update(wine);
        });

        cart.setAvailable(false);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Set<CartItem> getCartItems() {
        return getCart(userService.getPrincipalId()).getCartItems();
    }

    @Override
    public List<Cart> getHistory(Long userId) {
        return cartRepository.getOrdersHistory(userId);
    }

    private Cart getCart(Long userId) {
        List<Cart> carts = cartRepository.getAvailableCart(userId);
        if (carts.isEmpty()) {
            return createCartForCurrentUser();
        } else {
            return carts.get(0);
        }
    }

    private Cart createCartForCurrentUser() {
        Cart cart = new Cart();
        cart.setUser(userService.getPrincipal());
        cart.setAvailable(true);

        return cartRepository.save(cart);
    }
}

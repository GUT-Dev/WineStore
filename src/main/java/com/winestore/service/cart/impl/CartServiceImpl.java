package com.winestore.service.cart.impl;

import com.winestore.api.dto.cart.CartItemDTO;
import com.winestore.api.dto.cart.UserCartDTO;
import com.winestore.api.dto.product.WineListDTO;
import com.winestore.api.mapper.cart.CartItemMapper;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final WineService wineService;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional
    public void addToCurt(Long wineId, int amount) {
        Wine wine = wineService.getById(wineId);
        Cart cart = getCart(userService.getPrincipalId());

        CartItem cartItem = cartItemRepository.getByWineAndCart(wine, cart);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setWine(wine);
            cartItem.setAmount(amount);
            cartItem.setCart(cart);
        } else {
            cartItem.setAmount(cartItem.getAmount() + amount);
        }

        cartItemRepository.save(cartItem);
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
    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
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
    public UserCartDTO getCart() {
        UserCartDTO.UserCartDTOBuilder cart = UserCartDTO.builder();
        int totalPrice = 0;
        int totalPriceWithSale = 0;
        int totalSalePercent = 0;

        Set<CartItemDTO> items = getCart(userService.getPrincipalId()).getCartItems().stream()
            .map(cartItemMapper::toDTO)
            .collect(Collectors.toSet());

        for (CartItemDTO item : items) {
            WineListDTO wine = item.getWine();

            totalPrice += new BigDecimal(wine.getPrice()).unscaledValue().intValue() * item.getAmount();
            totalPriceWithSale += new BigDecimal(wine.getPriceWithSale()).unscaledValue().intValue() * item.getAmount();
        }

        if (totalPrice != totalPriceWithSale) {
            totalSalePercent = 100 - (100 * totalPriceWithSale / totalPrice);
        }

        return cart
            .items(items)
            .totalPrice(BigDecimal.valueOf(totalPrice).movePointLeft(2).toPlainString())
            .totalPriceWithSale(BigDecimal.valueOf(totalPriceWithSale).movePointLeft(2).toPlainString())
            .totalSalePercent(totalSalePercent)
            .build();
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

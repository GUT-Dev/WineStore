package com.winestore.service.cart.impl;

import com.winestore.api.dto.cart.*;
import com.winestore.api.dto.filters.OrdersFilter;
import com.winestore.api.dto.product.WineListDTO;
import com.winestore.api.mapper.cart.CartItemMapper;
import com.winestore.api.mapper.user.UserMapper;
import com.winestore.domain.entity.cart.Cart;
import com.winestore.domain.entity.cart.CartItem;
import com.winestore.domain.entity.product.Wine;
import com.winestore.domain.repository.cart.CartItemRepository;
import com.winestore.domain.repository.cart.CartRepository;
import com.winestore.domain.specification.CartSpecBuilder;
import com.winestore.enums.TrackingStatus;
import com.winestore.exception.OverProductAmountException;
import com.winestore.service.cart.CartService;
import com.winestore.service.product.WineService;
import com.winestore.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void addToCurt(Long wineId, int amount) {
        Wine wine = wineService.getById(wineId);

        if (wine.getSoldAmount() + amount > wine.getAmountForSale()) {
            throw new OverProductAmountException("The wine with id " + wine.getId()
                + " has just " + (wine.getAmountForSale() - wine.getSoldAmount()) +
                " pcs instead of " + amount);
        }

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
        Wine wine = cartItem.getWine();

        if (wine.getSoldAmount() + amount > wine.getAmountForSale()) {
            throw new OverProductAmountException("The wine with id " + wine.getId()
                + " has just " + (wine.getAmountForSale() - wine.getSoldAmount()) +
                " pcs instead of " + amount);
        }

        cartItem.setAmount(amount);
        cartItemRepository.save(cartItem);

        return getCart(userService.getPrincipalId());
    }

    @Override
    @Transactional
    public TrackingStatus changeStatus(ChangeStatusDTO dto) {
        TrackingStatus status = TrackingStatus.valueOf(dto.getStatus());

        Cart cart = cartRepository.getById(dto.getCartId());
        cart.setTrackingStatus(status);

        cart = cartRepository.save(cart);
        return cart.getTrackingStatus();
    }

    @Override
    @Transactional
    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    @Transactional
    public void buy() {
        Cart cart = getCart(userService.getPrincipalId());

        Set<CartItem> cartItems = cart.getCartItems();

        cartItems.forEach(item -> {
            if (item.isAvailable()) {
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
            } else {
                cartItemRepository.delete(item);
            }
        });

        cart.setAvailable(false);
        cart.setBuyDate(LocalDateTime.now());
        cart.setTrackingStatus(TrackingStatus.NEW);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public UserCartDTO getCart() {
        int totalPrice = 0;
        int totalPriceWithSale = 0;
        int totalSalePercent = 0;

        Set<CartItemDTO> items = getCart(userService.getPrincipalId()).getCartItems().stream()
            .map(this::updateCartItem)
            .map(cartItemMapper::toDTO)
            .collect(Collectors.toSet());

        for (CartItemDTO item : items) {
            if (item.isAvailable()) {
                WineListDTO wine = item.getWine();

                totalPrice += new BigDecimal(wine.getPrice()).unscaledValue().intValue() * item.getAmount();
                totalPriceWithSale += new BigDecimal(wine.getPriceWithSale()).unscaledValue().intValue() * item.getAmount();
            }
        }

        if (totalPrice != totalPriceWithSale) {
            totalSalePercent = 100 - (100 * totalPriceWithSale / totalPrice);
        }

        return UserCartDTO.builder()
            .items(items)
            .totalPrice(BigDecimal.valueOf(totalPrice).movePointLeft(2).toPlainString())
            .totalPriceWithSale(BigDecimal.valueOf(totalPriceWithSale).movePointLeft(2).toPlainString())
            .totalSalePercent(totalSalePercent)
            .build();
    }

    @Override
    @Transactional
    public List<CartHistoryDTO> getHistory() {
        List<Cart> carts = cartRepository.getOrdersHistory(userService.getPrincipalId());

        List<CartHistoryDTO> result = new ArrayList<>();

        for (Cart cart : carts) {
            int totalPrice = 0;
            int totalPriceWithSale = 0;
            int totalSalePercent = 0;

            Set<CartItemDTO> items = cart.getCartItems().stream()
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

            result.add(
                CartHistoryDTO.builder()
                    .id(cart.getId())
                    .trackingStatus(cart.getTrackingStatus())
                    .buyDate(cart.getBuyDate())
                    .items(items)
                    .totalPrice(BigDecimal.valueOf(totalPrice).movePointLeft(2).toPlainString())
                    .totalPriceWithSale(BigDecimal.valueOf(totalPriceWithSale).movePointLeft(2).toPlainString())
                    .totalSalePercent(totalSalePercent)
                    .build()
            );
        }

        return result;
    }

    @Override
    @Transactional
    public List<CartTrackingDTO> getOrders(OrdersFilter filter, Sort sort) {
        List<Cart> carts = cartRepository.findAll(getSpecification(filter), sort);

        List<CartTrackingDTO> result = new ArrayList<>();

        for (Cart cart : carts) {
            int totalPrice = 0;
            int totalPriceWithSale = 0;
            int totalSalePercent = 0;

            Set<CartItemDTO> items = cart.getCartItems().stream()
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

            result.add(
                CartTrackingDTO.builder()
                    .id(cart.getId())
                    .trackingStatus(cart.getTrackingStatus())
                    .buyDate(cart.getBuyDate())
                    .items(items)
                    .totalPrice(BigDecimal.valueOf(totalPrice).movePointLeft(2).toPlainString())
                    .totalPriceWithSale(BigDecimal.valueOf(totalPriceWithSale).movePointLeft(2).toPlainString())
                    .totalSalePercent(totalSalePercent)
                    .user(userMapper.toDTO(cart.getUser()))
                    .build()
            );
        }

        return result;
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
        cart.setCartItems(Collections.emptySet());

        return cartRepository.save(cart);
    }

    private CartItem updateCartItem(CartItem cartItem) {
        Wine wine = cartItem.getWine();
        cartItem.setAvailable(wine.getAmountForSale() > wine.getSoldAmount());
        return cartItemRepository.save(cartItem);
    }

    private Specification<Cart> getSpecification(OrdersFilter filter) {
        CartSpecBuilder builder = new CartSpecBuilder();

        builder.hasFirstName(filter.getFirstName());
        builder.hasLastName(filter.getLastName());
        builder.hasOrderId(filter.getOrderId());
        builder.hasStatus(filter.getStatus());
        builder.isEnabled(false);

        return builder.build();
    }
}

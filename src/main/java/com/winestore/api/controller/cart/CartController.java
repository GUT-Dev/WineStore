package com.winestore.api.controller.cart;

import com.winestore.api.dto.cart.CartItemDTO;
import com.winestore.api.dto.cart.PutInCartDTO;
import com.winestore.api.mapper.cart.CartItemMapper;
import com.winestore.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;
    private final CartItemMapper cartItemMapper;

    @GetMapping
    public Set<CartItemDTO> getCart() {
        return service.getCartItems().stream()
            .map(cartItemMapper::toDTO)
            .collect(Collectors.toSet());
    }

    @PutMapping
    public void putInCart(@RequestBody PutInCartDTO dto) {
        service.addToCurt(dto.getWineId(), dto.getAmount());
    }
}

package com.winestore.api.controller.cart;

import com.winestore.api.dto.cart.CartHistoryDTO;
import com.winestore.api.dto.cart.PutInCartDTO;
import com.winestore.api.dto.cart.UserCartDTO;
import com.winestore.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @GetMapping
    public UserCartDTO getCart() {
        return service.getCart();
    }

    @PutMapping
    public void putInCart(@RequestBody PutInCartDTO dto) {
        service.addToCurt(dto.getWineId(), dto.getAmount());
    }

    @DeleteMapping("/{id}")
    public void deleteFromCart(@PathVariable Long id) {
        service.removeFromCart(id);
    }

    @PutMapping("/buy")
    public void buy() {
        service.buy();
    }

    @GetMapping("/history")
    public List<CartHistoryDTO> getHistory() {
        return service.getHistory();
    }
}

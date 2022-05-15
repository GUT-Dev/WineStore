package com.winestore.api.controller.manager;

import com.winestore.api.dto.cart.CartTrackingDTO;
import com.winestore.api.dto.cart.ChangeStatusDTO;
import com.winestore.enums.TrackingStatus;
import com.winestore.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class CartOperations {

    private final CartService cartService;


    @GetMapping("/orders")
    public List<CartTrackingDTO> getOrders() {
        return cartService.getOrders();
    }

    @PutMapping("/orders/changeStatus")
    public TrackingStatus changeStatus(@RequestBody ChangeStatusDTO dto) {
        return cartService.changeStatus(dto);
    }
}

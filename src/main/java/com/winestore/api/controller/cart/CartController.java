package com.winestore.api.controller.cart;

import com.winestore.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.winestore.service.user.impl.UserServiceImpl.getPrincipalId;

@Controller
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("cart", service.getCartForUser(getPrincipalId()));

        return "cart";
    }
}

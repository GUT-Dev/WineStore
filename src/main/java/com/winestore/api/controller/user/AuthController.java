package com.winestore.api.controller.user;

import com.winestore.domain.entity.user.User;
import com.winestore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") User user
//                         BindingResult bindingResult
    ) {
//        if(userService.findUserByUsername(user.getUsername()) != null) {
//            bindingResult.addError(new FieldError("user", "username", "This username already exists"));
//            return "user/registration";
//        }
//        if(bindingResult.hasErrors()) {
//            return "user/registration";
//        }

        userService.create(user);
        return "redirect:wine";
    }

    @GetMapping("/registration")
    public String getForm(@ModelAttribute("user") User user) {
        return "auth/registrationForm";
    }
}

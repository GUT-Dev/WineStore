package com.winestore.api.controller.user;

import com.winestore.domain.entity.product.CustomerReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/review")
@RequiredArgsConstructor
public class CustomerReviewController {

    @PostMapping()
    public String createReview(@RequestBody CustomerReview review) {
        return "redirect:/wine";
    }
}

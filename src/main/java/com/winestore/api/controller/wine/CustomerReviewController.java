package com.winestore.api.controller.wine;

import com.winestore.domain.entity.product.CustomerReview;
import com.winestore.service.product.CustomerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/review")
@RequiredArgsConstructor
public class CustomerReviewController {

    private final CustomerReviewService service;

    @PostMapping()
    public String createReview(@RequestBody CustomerReview review) {
        return "redirect:/wine";
    }
}

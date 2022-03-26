package com.winestore.api.controller.wine;

import com.winestore.service.product.CustomerReviewService;
import com.winestore.service.product.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(("/wine"))
@RequiredArgsConstructor
public class WineController {

    private final WineService wineService;
    private final CustomerReviewService customerReviewService;

    @GetMapping("/{id}")
    public String getWine(@PathVariable(name = "id") Long id,
                          Model model) {
        model.addAttribute("item", wineService.getById(id));
        model.addAttribute("reviews", customerReviewService.getForWine(id));
        return "wine";
    }

    @GetMapping
    public String getPage(Model model,
                          Pageable pageable) {
        model.addAttribute("items", wineService.getPage(pageable));
        return "wines";
    }
}

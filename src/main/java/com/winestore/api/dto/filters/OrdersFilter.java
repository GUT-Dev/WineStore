package com.winestore.api.dto.filters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrdersFilter {
    private final String firstName;
    private final String lastName;
    private final List<String> status;
    private final Long orderId;
}

package com.winestore.service.cart;

import com.winestore.api.dto.cart.CartHistoryDTO;
import com.winestore.api.dto.cart.CartTrackingDTO;
import com.winestore.api.dto.cart.ChangeStatusDTO;
import com.winestore.api.dto.cart.UserCartDTO;
import com.winestore.api.dto.filters.OrdersFilter;
import com.winestore.domain.entity.cart.Cart;
import com.winestore.enums.TrackingStatus;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CartService {

    void addToCurt(Long wineId, int amount);

    Cart changeAmount(Long cartItemId, int amount);

    TrackingStatus changeStatus(ChangeStatusDTO dto);

    void removeFromCart(Long cartItemId);

    void buy();

    UserCartDTO getCart();

    List<CartHistoryDTO> getHistory();

    List<CartTrackingDTO> getOrders(OrdersFilter filter, Sort sort);
}

package com.winestore.domain.specification;

import com.winestore.domain.entity.cart.Cart;
import com.winestore.enums.TrackingStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class CartSpecBuilder {

    private final List<Specification<Cart>> specifications = new ArrayList<>();

    public void hasFirstName(String name) {
        if (name != null) {
            specifications.add((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("user").get("firstName")),
                    name.trim().toLowerCase(Locale.ROOT) + '%'));
        }
    }

    public void hasLastName(String name) {
        if (name != null) {
            specifications.add((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("user").get("lastName")),
                    name.trim().toLowerCase(Locale.ROOT) + '%'));
        }
    }

    public void hasOrderId(Long orderId) {
        if (orderId != null) {
            specifications.add((root, query, cb) ->
                cb.equal(root.get("id"), orderId));
        }
    }

    public void hasStatus(List<String> status) {
        if (status != null) {
            Set<Integer> statusOrdinals = status.stream()
                .map(value -> TrackingStatus.valueOf(value).ordinal())
                .collect(Collectors.toSet());

            specifications.add((root, query, cb) ->
                root.get("trackingStatus").in(statusOrdinals));
        }
    }

    public void isEnabled(Boolean isEnabled) {
        if (isEnabled != null) {
            specifications.add((root, query, cb) ->
                cb.equal(root.get("available"), isEnabled));
        }
    }

    public Specification<Cart> build() {
        if (specifications.isEmpty()) {
            return null;
        } else {
            Specification<Cart> spec = Specification.where(specifications.remove(0));
            return specifications.stream()
                .reduce(spec, Specification::and);
        }
    }
}

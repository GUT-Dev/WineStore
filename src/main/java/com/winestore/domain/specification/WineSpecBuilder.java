package com.winestore.domain.specification;

import com.winestore.domain.entity.product.Wine;
import com.winestore.enums.Sweetness;
import com.winestore.enums.WineType;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class WineSpecBuilder {

    private final List<Specification<Wine>> specifications = new ArrayList<>();

    public void hasPrice(Integer min, Integer max) {
        if (min != null && max != null) {
            specifications.add((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), min, max));
        }
    }

    public void hasTypes(List<String> types) {
        if (types != null) {
            Set<Integer> typeOrdinals = types.stream()
                .map(value -> WineType.valueOf(value).ordinal())
                .collect(Collectors.toSet());

            specifications.add((root, query, cb) ->
                root.get("type").in(typeOrdinals));
        }
    }

    public void hasSweetness(List<String> sweetness) {
        if (sweetness != null) {
            Set<Integer> sweetnessOrdinals = sweetness.stream()
                .map(value -> Sweetness.valueOf(value).ordinal())
                .collect(Collectors.toSet());

            specifications.add((root, query, cb) ->
                root.get("sweetness").in(sweetnessOrdinals));
        }
    }

    public void hasName(String name) {
        if (name != null) {
            specifications.add((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    '%' + name.toLowerCase(Locale.ROOT) + '%'));
        }
    }

    public void hasDiscount(Boolean hasDiscount) {
        if (hasDiscount != null) {
            specifications.add((root, query, cb) ->
                cb.notEqual(root.get("discount"), 0));
        }
    }

    public void isVisible(Boolean includeNotVisible) {
        if (includeNotVisible == null || !includeNotVisible) {
            specifications.add((root, query, cb) ->
                cb.equal(root.get("visible"), true));
        }
    }

    public Specification<Wine> build() {
        if (specifications.isEmpty()) {
            return null;
        } else {
            Specification<Wine> spec = Specification.where(specifications.remove(0));
            return specifications.stream()
                .reduce(spec, Specification::and);
        }
    }
}

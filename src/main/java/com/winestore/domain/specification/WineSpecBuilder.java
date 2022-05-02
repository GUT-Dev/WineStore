package com.winestore.domain.specification;

import com.winestore.domain.entity.product.Wine;
import com.winestore.enums.Sweetness;
import com.winestore.enums.WineType;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class WineSpecBuilder {

    private final List<Specification<Wine>> specifications = new ArrayList<>();

    public WineSpecBuilder hasPrice() {
        return this;
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

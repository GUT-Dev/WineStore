package com.winestore.service.product.impl;

import com.winestore.api.dto.filters.WineSearchFilter;
import com.winestore.domain.entity.product.Wine;
import com.winestore.domain.repository.product.WineRepository;
import com.winestore.domain.specification.WineSpecBuilder;
import com.winestore.service.product.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WineServiceImpl implements WineService {

    private final WineRepository repository;

    @Override
    public Wine getById(Long id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Wine create(Wine wine) {
        return repository.save(wine);
    }

    @Override
    public Wine update(Wine wine) {
        if (repository.existsById(wine.getId())) {
            return repository.save(wine);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Wine> getPage(WineSearchFilter filter, Pageable pageable) {
        Page<Wine> winesPage = repository.findAll(getSpecification(filter), pageable);
        List<Wine> wines = winesPage.stream()
            .peek(w -> w.setRating(countRating(w.getId())))
            .toList();
        return new PageImpl<>(wines, pageable, winesPage.getTotalElements());
    }

    @Override
    public int countRating(Long wineId) {
        return Optional.ofNullable(repository.countRating(wineId)).orElse(0);
    }

    private Specification<Wine> getSpecification(WineSearchFilter filter) {
        WineSpecBuilder builder = new WineSpecBuilder();

        builder.hasSweetness(filter.getSweetness());
        builder.hasTypes(filter.getType());
        builder.hasPrice(filter.getMinPrice() * 100, filter.getMaxPrice() * 100);
        builder.hasName(filter.getName());
        builder.hasDiscount(filter.getHasDiscount());
        builder.isVisible(filter.getIncludeNotVisible());

        return builder.build();
    }

    @Override
    public BigDecimal getMaxPrice() {
        return repository.getMaxPrice();
    }

    @Override
    public BigDecimal getMinPrice() {
        return repository.getMinPrice();
    }
}

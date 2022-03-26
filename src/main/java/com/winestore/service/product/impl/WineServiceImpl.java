package com.winestore.service.product.impl;

import com.winestore.domain.dto.WineViewDTO;
import com.winestore.domain.entity.product.Wine;
import com.winestore.domain.repository.product.WineRepository;
import com.winestore.service.product.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class WineServiceImpl implements WineService {

    private final WineRepository repository;

    @Override
    public WineViewDTO getById(Long id) {
        return countRating(repository.findById(id)
            .orElseThrow(EntityNotFoundException::new));
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
    public Page<WineViewDTO> getPage(Pageable pageable) {
        return repository.findAll(pageable)
            .map(this::countRating);
    }

    private Wine map(Wine wine) {
        wine.setPrice(wine.getPrice().movePointLeft(2));
        return wine;
    }

    private WineViewDTO countRating(Wine wine) {
        WineViewDTO wineViewDTO = new WineViewDTO();
        BigDecimal rating = repository.countRating(wine.getId());
        rating = rating != null
            ? rating.setScale(2, RoundingMode.HALF_EVEN)
            : new BigDecimal("0");
        
        wineViewDTO.setWine(map(wine));
        wineViewDTO.setRating(rating.doubleValue());

        return wineViewDTO;
    }
}

package com.winestore.service.product.impl;

import com.winestore.domain.entity.product.Wine;
import com.winestore.domain.repository.product.WineRepository;
import com.winestore.service.product.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
    public Page<Wine> getPage(Pageable pageable) {
        return repository.getPage(pageable);
    }
}

package com.winestore.service.product.impl;

import com.winestore.domain.entity.product.Land;
import com.winestore.domain.repository.product.LandRepository;
import com.winestore.service.product.LandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LandServiceImpl implements LandService {

    private final LandRepository repository;

    public Land getById(Long id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Land create(Land land) {
        return repository.save(land);
    }

    @Override
    public Land update(Land land) {
        if (repository.existsById(land.getId())) {
            return repository.save(land);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Land> getAll() {
        return repository.findAll();
    }
}

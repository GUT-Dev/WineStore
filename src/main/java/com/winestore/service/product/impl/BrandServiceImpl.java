package com.winestore.service.product.impl;

import com.winestore.domain.entity.product.Brand;
import com.winestore.domain.repository.product.BrandRepository;
import com.winestore.service.product.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;

    public Brand getById(Long id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Brand create(Brand land) {
        return repository.save(land);
    }

    @Override
    public Brand update(Brand land) {
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
    public List<Brand> getAll() {
        return repository.findAll();
    }
}

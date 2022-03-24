package com.winestore.service.product.impl;

import com.winestore.domain.entity.product.CustomerReview;
import com.winestore.domain.entity.product.UserWinePK;
import com.winestore.domain.repository.product.CustomerReviewRepository;
import com.winestore.service.product.CustomerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomerReviewServiceImpl implements CustomerReviewService {

    private final CustomerReviewRepository repository;

    public CustomerReview getById(UserWinePK id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public CustomerReview create(CustomerReview customerReview) {
        return repository.save(customerReview);
    }

    @Override
    public CustomerReview update(CustomerReview customerReview) {
        if (repository.existsById(customerReview.getId())) {
            return repository.save(customerReview);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void delete(UserWinePK id) {
        repository.deleteById(id);
    }

    @Override
    public Page<CustomerReview> getPageForWine(Long wineId, Pageable pageable) {
        return repository.getAllByWineId(wineId, pageable);
    }
}

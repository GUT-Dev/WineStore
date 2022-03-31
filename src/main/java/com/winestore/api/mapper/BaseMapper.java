package com.winestore.api.mapper;

public interface BaseMapper<E, D> {
    D toDTO(E entity);

    E toEntity(D dto);
}

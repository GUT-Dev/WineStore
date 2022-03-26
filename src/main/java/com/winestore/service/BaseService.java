package com.winestore.service;

public interface BaseService<T> {
    T create(T t);

    T update(T t);
}

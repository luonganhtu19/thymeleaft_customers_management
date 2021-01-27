package com.tu.service;

import java.util.List;

public interface Service<T> {
    List<T> findAll();
    T findById(Integer id);
    void update(Integer id,T model);
    void save(T model);
    void remove(Integer id);
}

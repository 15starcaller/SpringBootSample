package com.cko.sampleSpringProject.dao;

import com.cko.sampleSpringProject.model.Film;
import com.cko.sampleSpringProject.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDAO extends CrudRepository<Product, Long> {
    List<Product> findAll();
    Product findProductById(Long id);
}
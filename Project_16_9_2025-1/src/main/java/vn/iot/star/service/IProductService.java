package vn.iot.star.service;

import java.util.List;
import java.util.Optional;

import vn.iot.star.entity.Product;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(int id);
    Optional<Product> findByProductName(String name);
    Product save(Product product);
    void delete(Product product);
}
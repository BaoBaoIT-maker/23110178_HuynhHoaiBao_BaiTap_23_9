package vn.iot.star.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.iot.star.entity.Category;

public interface CategoryService {
    List<Category> findAll();
    public Category findById(int id);
    void deleteById(int id);
    List<Category> findByCategoryNameContaining(String name);
    Page<Category> findAll(Pageable pageable);
    Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
    void delete(Category entity);
    int count();
    <S extends Category> Optional<S> findOne(Example<S> example);
    Optional<Category> findById(Integer id);
    List<Category> findAllById(Iterable<Integer> ids);
    List<Category> findAll(Sort sort);
    Optional<Category> findByName(String name);
    <S extends Category> S save(S entity);
}

package vn.iot.star.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import vn.iot.star.entity.Category;
import vn.iot.star.repository.CategoryRepository;
import vn.iot.star.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public <S extends Category> S save(S entity) {
    if(entity.getId() == null) {
    return categoryRepository.save(entity);
    }else {
	    Optional<Category> opt = findById(entity.getId());
	    }
    	return categoryRepository.save(entity);
    }
    @Override
    public List<Category> findAllById(Iterable<Integer> ids) {
    	return categoryRepository.findAllById(ids);
    }
    @Override
    public Optional<Category> findById(Integer id) {
    	return categoryRepository.findById(id);
    }
    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
    return categoryRepository.findOne(example);
    }
    @Override
    public int count() {
    return (int) categoryRepository.count();
    }
    @Override
    public List<Category> findAll(Sort sort) {
    return categoryRepository.findAll(sort);
    }

    @Override
    public Optional<Category> findByName(String name) {
    	return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
    @Override
    public void delete(Category entity) {
    categoryRepository.delete(entity);
    }
    @Override
    public Page<Category> findByCategoryNameContaining(String name, Pageable pageable) {
        return categoryRepository.findByNameContaining(name, pageable);
    }
    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);;
    }

    @Override
    public List<Category> findByCategoryNameContaining(String name) {
        return categoryRepository.findByNameContaining(name);
    }
}
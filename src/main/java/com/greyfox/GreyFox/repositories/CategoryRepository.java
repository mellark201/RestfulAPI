package com.greyfox.GreyFox.repositories;

import com.greyfox.GreyFox.domain.Category;
import com.greyfox.GreyFox.exceptions.ETBadRequestException;
import com.greyfox.GreyFox.exceptions.ETResourceNotFoundException;

import java.util.List;

public interface CategoryRepository {


    List<Category> findAll(Integer userId) throws ETResourceNotFoundException;

    Category findById(Integer userId, Integer categoryId) throws ETResourceNotFoundException;

    Integer create(Integer userId, String title, String description) throws ETBadRequestException;

    void update(Integer userId, Integer categoryId, Category category) throws ETBadRequestException;

    void removeById(Integer userId, Integer categoryId) throws ETResourceNotFoundException;
}

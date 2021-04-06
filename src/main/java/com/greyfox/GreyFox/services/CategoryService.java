package com.greyfox.GreyFox.services;


import com.greyfox.GreyFox.domain.Category;
import com.greyfox.GreyFox.exceptions.ETBadRequestException;
import com.greyfox.GreyFox.exceptions.ETResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> fetchAllCategories(Integer userId);
    Category fetchCategoryById(Integer userId, Integer categoryId) throws ETResourceNotFoundException;
    Category addCategory(Integer userId, String title, String description) throws ETBadRequestException;
    void updateCategory(Integer userId, Integer categoryId, Category category) throws ETBadRequestException;
    void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws ETResourceNotFoundException;
}

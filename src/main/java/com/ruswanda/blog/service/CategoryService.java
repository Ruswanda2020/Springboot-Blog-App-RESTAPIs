package com.ruswanda.blog.service;

import com.ruswanda.blog.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long categoryId);

    List<CategoryDto> getAllCategory();
    CategoryDto updateCateCategory(CategoryDto dto, Long id);
    void deleteById(Long id);
}

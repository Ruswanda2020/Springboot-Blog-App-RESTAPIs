package com.ruswanda.blog.service.impl;

import com.ruswanda.blog.dto.CategoryDto;
import com.ruswanda.blog.entity.Category;
import com.ruswanda.blog.exception.ResourceNotFoundException;
import com.ruswanda.blog.repository.CategoryRepository;
import com.ruswanda.blog.service.CategoryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : blog
 * User: Ruswanda
 * Email: wandasukabumi2020@gmail.com
 * Telegram : @Ruswanda
 * Date: 20/12/23
 * Time: 08.47
 */

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(category);
        return modelMapper.map(saveCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
    }

    @Override
    public CategoryDto updateCateCategory(CategoryDto dto, Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("category", "id", id));

        category.setName(dto.getName() == null || dto.getName().isEmpty() ?
                category.getName() : dto.getName());
        category.setDescription(dto.getDescription() == null || dto.getDescription().isEmpty() ?
                category.getDescription() : dto.getDescription());

        Category categoryUpdate = categoryRepository.save(category);
        return modelMapper.map(categoryUpdate, CategoryDto.class);
    }

    @Override
    public void deleteById(Long id) {
       Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("category", "id", id));
       categoryRepository.delete(category);
    }
}

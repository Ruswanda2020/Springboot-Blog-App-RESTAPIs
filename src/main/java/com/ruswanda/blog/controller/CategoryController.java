package com.ruswanda.blog.controller;

import com.ruswanda.blog.dto.CategoryDto;
import com.ruswanda.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
@Tag(
        name = "CRUD REST APIs Category Resource "
)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addNewCategory(@RequestBody CategoryDto categoryDto){
        log.info("Received a request to add a new category with data: {}", categoryDto);
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        log.info("New category added successfully. Category details: {}", savedCategory);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id){

        log.info("Received a request to find a category by ID: {}", id);
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        log.info("Category found for ID {}: {}", id, categoryDto);
        return ResponseEntity.ok(categoryDto);
    }


    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll(){
        log.info("Received a request to fetch all categories.");
        List<CategoryDto> allCategories = categoryService.getAllCategory();
        return ResponseEntity.ok(allCategories);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto dto,
                                                      @PathVariable Long id){
        log.info("Received a request to find a category by ID: {}", id);
        CategoryDto updatedCategory = categoryService.updateCateCategory(dto, id);
        log.info("Category with ID {} updated successfully. Updated details: {}", id, updatedCategory);
        return ResponseEntity.ok(updatedCategory);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){

        log.info("Received a request to delete category with ID: {}", id);
        categoryService.deleteById(id);
        log.info("Category with ID {} deleted successfully.", id);
        return ResponseEntity.ok("Category deleted successfully!");
    }

}

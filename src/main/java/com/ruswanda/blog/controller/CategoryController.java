package com.ruswanda.blog.controller;

import com.ruswanda.blog.dto.CategoryDto;
import com.ruswanda.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto dto,
                                                      @PathVariable Long id){
        return ResponseEntity.ok(categoryService.updateCateCategory(dto, id));
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully!");
    }

}

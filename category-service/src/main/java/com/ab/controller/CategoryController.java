package com.ab.controller;

import com.ab.model.Category;
import com.ab.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/salon/{id}")
    public ResponseEntity<Set<Category>> getCategoriesBySalon(@PathVariable Long id){

        Set<Category> allCategories = categoryService.getAllCategoriesBySalonId(id);
        return ResponseEntity.ok(allCategories);
    }

     @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){

         Category category = categoryService.getCategoryById(id);
         return ResponseEntity.ok(category);
    }




}

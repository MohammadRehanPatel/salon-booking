package com.ab.service;

import com.ab.model.Category;
import com.ab.payload.dto.SalonDTO;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDTO salonDTO);

    Set<Category> getAllCategoriesBySalonId(Long salonId);

    Category getCategoryById(Long id);
    void deleteCategoryById(Long id,Long salonId);

}

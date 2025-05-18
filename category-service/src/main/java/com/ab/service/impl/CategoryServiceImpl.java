package com.ab.service.impl;

import com.ab.exception.CategoryException;
import com.ab.model.Category;
import com.ab.payload.dto.SalonDTO;
import com.ab.repository.CategoryRepository;
import com.ab.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setSalonId(salonDTO.getId());
        newCategory.setImage(category.getImage());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalonId(Long salonId) {

        return categoryRepository.findBySalonId(salonId);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryException("Category Not Exist with id "+ id));
    }

    @Override
    public void deleteCategoryById(Long id,Long salonId) {
        Category category = getCategoryById(id);
        if(!category.getSalonId().equals(salonId)){
            throw new CategoryException("you don;t have permission to delete this category");
        }
        categoryRepository.deleteById(id);
    }
}

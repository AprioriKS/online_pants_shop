package com.example.online_pants_shop.services.impl;

import com.example.online_pants_shop.dto.category.request.CreateCategoryDto;
import com.example.online_pants_shop.dto.category.request.UpdateCategoryDto;
import com.example.online_pants_shop.dto.category.response.CategoryResponseDto;
import com.example.online_pants_shop.entities.Category;
import com.example.online_pants_shop.exception.DuplicateEntryException;
import com.example.online_pants_shop.exception.EntityNotFoundException;
import com.example.online_pants_shop.mappers.CategoryMapper;
import com.example.online_pants_shop.repositories.CategoryRepository;
import com.example.online_pants_shop.services.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Override
  @Transactional
  public CategoryResponseDto save(CreateCategoryDto categoryDto)  {
    if (categoryRepository.existsByName(categoryDto.name())) {
      throw new DuplicateEntryException("Category " + categoryDto.name() + " already exist "
          + "in DB");
    }
    Category category = categoryMapper.toCreateModel(categoryDto);
    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.toCategoryDto(savedCategory);
  }

  @Override
  public List<CategoryResponseDto> findAll(Pageable pageable) {
    return categoryRepository.findAll(pageable).stream()
        .map(categoryMapper::toCategoryDto)
        .toList();
  }

  @Override
  public CategoryResponseDto findById(Long id)  {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
    return categoryMapper.toCategoryDto(category);
  }

  @Override
  @Transactional
  public CategoryResponseDto update(UpdateCategoryDto updateCategoryDto, Long id) {      
    Category existingCategory = categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));

    Category updatedData = categoryMapper.toUpdateModel(updateCategoryDto);
    existingCategory.setName(updatedData.getName());
    existingCategory.setDescription(updatedData.getDescription());

    Category savedCategory = categoryRepository.save(existingCategory);

    return categoryMapper.toCategoryDto(savedCategory);
  }

  @Override
  public void deleteById(Long id)  {
    if (!categoryRepository.existsById(id)) {
      throw new EntityNotFoundException("Category not found with id " + id);
    }
    categoryRepository.deleteById(id);
  }
}

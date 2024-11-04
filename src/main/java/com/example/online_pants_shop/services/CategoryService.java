package com.example.online_pants_shop.services;

import com.example.online_pants_shop.dto.category.request.CreateCategoryDto;
import com.example.online_pants_shop.dto.category.request.UpdateCategoryDto;
import com.example.online_pants_shop.dto.category.response.CategoryResponseDto;
import com.example.online_pants_shop.exception.DuplicateEntryException;
import com.example.online_pants_shop.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
  CategoryResponseDto save(CreateCategoryDto categoryDto);

  List<CategoryResponseDto> findAll(Pageable pageable);

  CategoryResponseDto findById(Long id);

  CategoryResponseDto update(UpdateCategoryDto updateCategoryDto, Long id);

  void deleteById(Long id);
}

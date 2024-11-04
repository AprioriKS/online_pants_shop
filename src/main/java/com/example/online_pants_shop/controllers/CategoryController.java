package com.example.online_pants_shop.controllers;

import com.example.online_pants_shop.controllers.constant.ShopConstants;
import com.example.online_pants_shop.dto.category.request.CreateCategoryDto;
import com.example.online_pants_shop.dto.category.request.UpdateCategoryDto;
import com.example.online_pants_shop.dto.category.response.CategoryResponseDto;
import com.example.online_pants_shop.exception.DuplicateEntryException;
import com.example.online_pants_shop.exception.EntityNotFoundException;
import com.example.online_pants_shop.repositories.CategoryRepository;
import com.example.online_pants_shop.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @Operation(summary = "Create a new category", description = "Saves a new category to the database.")
  @PostMapping
  public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CreateCategoryDto createCategoryDto)  {
      CategoryResponseDto createdCategory = categoryService.save(createCategoryDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
  }

  @Operation(summary = "Get category by ID", description = "Retrieves a category by its ID.")
  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id)
      throws EntityNotFoundException {
    CategoryResponseDto category = categoryService.findById(id);
    return ResponseEntity.ok(category);
  }

  @Operation(summary = "Get all categories", description = "Retrieves a list of all categories.")
  @GetMapping
  public ResponseEntity<List<CategoryResponseDto>> getAllCategories(
      @Parameter(example = ShopConstants.PAGEABLE_EXAMPLE_NAME) Pageable pageable) {
    List<CategoryResponseDto> categories = categoryService.findAll(pageable);
    return ResponseEntity.ok(categories);
  }

  @Operation(summary = "Update an existing category", description = "Update a category by its ID.")
  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryDto updateCategoryDto) {
    CategoryResponseDto updatedCategory = categoryService.update(updateCategoryDto, id);
    return ResponseEntity.ok(updatedCategory);
  }

  @Operation(summary = "Delete category by ID", description = "Delete a category by its ID.")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
      throws EntityNotFoundException {
    categoryService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}

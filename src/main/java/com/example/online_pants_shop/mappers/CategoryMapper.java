package com.example.online_pants_shop.mappers;

import com.example.online_pants_shop.config.MapperConfig;
import com.example.online_pants_shop.dto.category.request.CreateCategoryDto;
import com.example.online_pants_shop.dto.category.request.UpdateCategoryDto;
import com.example.online_pants_shop.dto.category.response.CategoryResponseDto;
import com.example.online_pants_shop.entities.Category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
  CategoryResponseDto toCategoryDto(Category category);

  @Mapping(target = "id", ignore = true)
  Category toCreateModel(CreateCategoryDto categoryDto);

  @Mapping(target = "id", ignore = true)
  Category toUpdateModel(UpdateCategoryDto categoryDto);
}

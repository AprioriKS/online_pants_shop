package com.example.online_pants_shop.mappers;

import com.example.online_pants_shop.config.MapperConfig;
import com.example.online_pants_shop.dto.product.request.CreateProductDto;
import com.example.online_pants_shop.dto.product.request.UpdateProductDto;
import com.example.online_pants_shop.dto.product.response.ProductResponseDto;
import com.example.online_pants_shop.entities.Category;
import com.example.online_pants_shop.entities.Product;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {
  @Mapping(target = "categoryIds", ignore = true)
  ProductResponseDto toDto(Product product);

  @AfterMapping
  default void setCategoryIds(@MappingTarget ProductResponseDto ProductResponseDto, Product product) {
    Set<Long> categoryIds = product.getCategories().stream()
        .map(Category::getId)
        .collect(Collectors.toSet());
    ProductResponseDto.setCategoryIds(categoryIds);
  }

  @Mapping(target = "categories", ignore = true)
  @Mapping(target = "id", ignore = true)
  Product toCreateModel(CreateProductDto requestDto);

  @Mapping(target = "categories", ignore = true)
  Product toUpdateModel(UpdateProductDto requestDto);

  @AfterMapping
  default void setCategories(@MappingTarget Product product, CreateProductDto ProductResponseDto) {
    Set<Category> categories = ProductResponseDto.getCategoryIds().stream()
        .map(Category::new)
        .collect(Collectors.toSet());
    product.setCategories(categories);
  }

  @AfterMapping
  default void setCategories(@MappingTarget Product product, UpdateProductDto ProductResponseDto) {
    Set<Category> categories = ProductResponseDto.getCategoryIds().stream()
        .map(Category::new)
        .collect(Collectors.toSet());
    product.setCategories(categories);
  }

}

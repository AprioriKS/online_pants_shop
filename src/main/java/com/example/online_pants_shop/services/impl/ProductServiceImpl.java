package com.example.online_pants_shop.services.impl;

import com.example.online_pants_shop.dto.category.request.ProductSearchParametersDto;
import com.example.online_pants_shop.dto.product.request.CreateProductDto;
import com.example.online_pants_shop.dto.product.request.UpdateProductDto;
import com.example.online_pants_shop.dto.product.response.ProductResponseDto;
import com.example.online_pants_shop.exception.DuplicateEntryException;
import com.example.online_pants_shop.exception.EntityNotFoundException;
import com.example.online_pants_shop.mappers.ProductMapper;
import com.example.online_pants_shop.repositories.CategoryRepository;
import com.example.online_pants_shop.repositories.ProductRepository;
import com.example.online_pants_shop.services.ProductService;
import java.util.HashSet;
import java.util.List;
import com.example.online_pants_shop.entities.Product;
import com.example.online_pants_shop.entities.Category;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ProductMapper productMapper;

  @Override
  @Transactional
  public ProductResponseDto save(CreateProductDto requestDto)  {
    if (productRepository.existsByName(requestDto.getName())) {
      throw new DuplicateEntryException("Products " + requestDto.getName() + " already exist "
          + "in DB");
    }
    Product product = productMapper.toCreateModel(requestDto);
    Set<Category> categories = new HashSet<>();

    for (Long categoryId : requestDto.getCategoryIds()) {
      Category category = categoryRepository.findById(categoryId).orElse(null);
      if (category == null) {
        category = new Category();
        category.setId(categoryId);
        categoryRepository.save(category);
      }
      categories.add(category);
    }

    product.setCategories(categories);
    Product savedProduct = productRepository.save(product);

    return productMapper.toDto(savedProduct);
  }

  @Override
  @Transactional
  public List<ProductResponseDto> findAll(Pageable pageable) {
    return productRepository.findAll(pageable).stream()
        .map(productMapper::toDto)
        .toList();

  }

  @Override
  @Transactional
  public ProductResponseDto findById(Long id)  {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

    return productMapper.toDto(product);
  }

  @Override
  @Transactional
  public ProductResponseDto update(UpdateProductDto requestDto, Long id,
      boolean areCategoriesReplaced)  {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

    if (requestDto.getName() != null) {
      product.setName(requestDto.getName());
    }
    if (requestDto.getBrand() != null) {
      product.setBrand(requestDto.getBrand());
    }
    if (requestDto.getColor() != null) {
      product.setColor(requestDto.getColor());
    }
    if (requestDto.getPrice() != null) {
      product.setPrice(requestDto.getPrice());
    }
    if (requestDto.getStock() != null) {
      product.setStock(requestDto.getStock());
    }
    if (requestDto.getDescription() != null) {
      product.setDescription(requestDto.getDescription());
    }
    if (requestDto.getImageUrl() != null) {
      product.setImageUrl(requestDto.getImageUrl());
    }
    if (requestDto.getIsSales() != null) {
      product.setSales(requestDto.getIsSales());
    }
    if (requestDto.getSize() != null) {
      product.setSize(requestDto.getSize());
    }
    if (requestDto.getRating() != null) {
      product.setRating(requestDto.getRating());
    }
    if (requestDto.getStyle() != null) {
      product.setStyle(requestDto.getStyle());
    }


    if (areCategoriesReplaced) {
      Set<Category> newCategories = requestDto.getCategoryIds().stream()
          .map(Category::new)
          .collect(Collectors.toSet());
      product.setCategories(newCategories);
    } else {
      Set<Category> existingCategories = product.getCategories();
      Set<Category> additionalCategories = requestDto.getCategoryIds().stream()
          .map(Category::new)
          .collect(Collectors.toSet());
      existingCategories.addAll(additionalCategories);
      product.setCategories(existingCategories);
    }

    productRepository.save(product);
    return productMapper.toDto(product);
  }

  @Override
  public void delete(Long id) {
    if (!productRepository.existsById(id)) {
      throw new EntityNotFoundException("Product not found with id: " + id);
    }
    productRepository.deleteById(id);
  }

  @Override
  public List<ProductResponseDto> search(ProductSearchParametersDto searchParameters) {
    return List.of(); //тут буде реалізований фільтер
  }

  @Override
  @Transactional
  public List<ProductResponseDto> findByCategoryId(Long categoryId) {
    if (categoryId == null) {
      throw new IllegalArgumentException("Category ID cannot be null");
    }
    List<Product> products = productRepository.findByCategories_Id(categoryId);
    return products.stream()
        .map(productMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public List<ProductResponseDto> findOnSale() {
    List<Product> productsOnSale = productRepository.findByIsSalesTrue();
    return productsOnSale.stream()
        .map(productMapper::toDto)
        .collect(Collectors.toList());
  }
}

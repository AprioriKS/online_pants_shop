package com.example.online_pants_shop.services;

import com.example.online_pants_shop.dto.category.request.ProductSearchParametersDto;
import com.example.online_pants_shop.dto.product.request.CreateProductDto;
import com.example.online_pants_shop.dto.product.request.UpdateProductDto;
import com.example.online_pants_shop.dto.product.response.ProductResponseDto;
import com.example.online_pants_shop.exception.DuplicateEntryException;
import com.example.online_pants_shop.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  ProductResponseDto save(CreateProductDto requestDto);

  List<ProductResponseDto> findAll(Pageable pageable);

  ProductResponseDto findById(Long id) ;

  ProductResponseDto update(UpdateProductDto requestDto, Long id, boolean areCategoriesReplaced);

  void delete(Long id);

  List<ProductResponseDto> search(ProductSearchParametersDto searchParameters);

  List<ProductResponseDto> findByCategoryId(Long categoryId);

  List<ProductResponseDto> findOnSale();
}

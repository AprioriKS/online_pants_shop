package com.example.online_pants_shop.repositories;

import com.example.online_pants_shop.dto.category.request.ProductSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
  Specification<T> build(ProductSearchParametersDto searchParameters);
}

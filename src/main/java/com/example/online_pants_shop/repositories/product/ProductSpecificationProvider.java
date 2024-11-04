package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public interface ProductSpecificationProvider<T> {
  String getKey();
  Specification<Product> getSpecification(String[] params);

}

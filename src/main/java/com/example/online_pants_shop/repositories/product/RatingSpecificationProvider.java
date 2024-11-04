package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RatingSpecificationProvider implements ProductSpecificationProvider<Product> {
  @Override
  public String getKey() {
    return "rating";
  }

  @Override
  public Specification<Product> getSpecification(String[] params) {
    int rating = Integer.parseInt(params[0]);
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(
        "rating"), rating);
  }
}

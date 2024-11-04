package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AvailabilitySpecificationProvider implements ProductSpecificationProvider<Product>{

  @Override
  public String getKey() {
    return "stock";
  }

  @Override
  public Specification<Product> getSpecification(String[] params) {
    int stock = Integer.parseInt(params[0]);
    if (stock < 1) {
      return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
          root.get("stock"),
          1);
    }
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("stock"),
        stock);
  }



}

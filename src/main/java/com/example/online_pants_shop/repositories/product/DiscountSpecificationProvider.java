package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DiscountSpecificationProvider implements ProductSpecificationProvider<Product>{
  @Override
  public String getKey() {
    return "isSales";
  }

  @Override
  public Specification<Product> getSpecification(String[] params) {
    Boolean hasDiscount = Boolean.parseBoolean(params[0]);
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isSales"), hasDiscount);
  }
}

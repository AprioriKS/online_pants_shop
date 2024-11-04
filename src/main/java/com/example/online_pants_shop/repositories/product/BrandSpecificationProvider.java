package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BrandSpecificationProvider implements ProductSpecificationProvider<Product>{

  @Override
  public String getKey() {
    return "brand";
  }

  public Specification<Product> getSpecification(String[] params) {
    return (root, query, criteriaBuilder) -> root.get("brand").in(Arrays.stream(params).toArray());
  }
}

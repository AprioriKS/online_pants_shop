package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ColorSpecificationProvider implements ProductSpecificationProvider<Product> {

  @Override
  public String getKey() {
    return "color";
  }

  public Specification<Product> getSpecification(String[] params) {
    return (root, query, criteriaBuilder) -> root.get("color").in(Arrays.stream(params).toArray());
  }
}

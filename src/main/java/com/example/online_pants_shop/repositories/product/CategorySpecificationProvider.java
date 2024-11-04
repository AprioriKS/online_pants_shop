package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategorySpecificationProvider implements ProductSpecificationProvider<Product> {

  @Override
  public String getKey() {
    return "categories";
  }

  public Specification<Product> getSpecification(String[] params) {
    List<Integer> categoryIds = Arrays.stream(params)
        .map(param -> {
          try {
            return Integer.parseInt(param);
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid category ID format: " + param);
          }
        })
        .collect(Collectors.toList());

    return (root, query, criteriaBuilder) -> {
      var categoryJoin = root.join("categories"); // Join with Category
      return categoryJoin.get("id").in(categoryIds); // Use parsed IDs
    };

  }
}

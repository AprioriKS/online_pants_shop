package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.dto.category.request.ProductSearchParametersDto;
import com.example.online_pants_shop.entities.Product;
import com.example.online_pants_shop.repositories.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductSpecificationBuilder implements SpecificationBuilder<Product> {

  private final SpecificationProviderManager<Product> productSpecificationProviderManager;

  @Override
  public Specification<Product> build(ProductSearchParametersDto searchParameters) {
   Specification<Product> spec = Specification.where(null);
    if (searchParameters.getStock() > 1) {
      spec =
          spec.and(productSpecificationProviderManager.getSpecificationProvider("stock").getSpecification(new String[]{String
              .valueOf(searchParameters.getStock())}));
    }
    if (searchParameters.getBrand() != null && searchParameters.getBrand().length > 0) {
      spec =
          spec.and(productSpecificationProviderManager.getSpecificationProvider("brand").getSpecification(searchParameters.getBrand()));
    }
    if (searchParameters.getCategories() != null && searchParameters.getCategories().length > 0) {
      spec = spec.and(productSpecificationProviderManager
          .getSpecificationProvider("categories")
          .getSpecification(searchParameters.getCategories()));
    }
    if (searchParameters.getColor() != null && searchParameters.getColor().length > 0) {
      spec =
          spec.and(productSpecificationProviderManager.getSpecificationProvider("color").getSpecification(searchParameters.getColor()));
    }
    if (searchParameters.getIsSales() != null) {
      spec =
          spec = spec.and(productSpecificationProviderManager.getSpecificationProvider("isSales")
              .getSpecification(new String[]{String.valueOf(searchParameters.getIsSales())}));
    }
    if (searchParameters.getFromPrice() != null) {
      spec = spec.and(productSpecificationProviderManager
          .getSpecificationProvider("fromPrice")
          .getSpecification(new String[]{String
              .valueOf(searchParameters.getFromPrice())}));
    }
    if (searchParameters.getToPrice() != null) {
      spec = spec.and(productSpecificationProviderManager
          .getSpecificationProvider("toPrice")
          .getSpecification(new String[]{String
              .valueOf(searchParameters.getToPrice())}));
    }
    if (searchParameters.getRating() > 0) {
      spec = spec.and(productSpecificationProviderManager
          .getSpecificationProvider("rating")
          .getSpecification(new String[]{String
              .valueOf(searchParameters.getRating())}));
    }
    if (searchParameters.getSize() != null && searchParameters.getSize().length > 0) {
      spec =
          spec.and(productSpecificationProviderManager.getSpecificationProvider("size").getSpecification(searchParameters.getSize()));
    }
    if (searchParameters.getStyle() != null && searchParameters.getStyle().length > 0) {
      spec =
          spec.and(productSpecificationProviderManager.getSpecificationProvider("style").getSpecification(searchParameters.getStyle()));
    }
   return spec;
  }
}

package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductSpecificationProviderManager implements SpecificationProviderManager<Product> {
  private final List<ProductSpecificationProvider<Product>> productSpecificationProvider;
  @Override
  public ProductSpecificationProvider<Product> getSpecificationProvider(String key) {
    return productSpecificationProvider.stream()
        .filter(p -> p.getKey().equals(key))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Can`t find corect specification provider for key" + key));
  }
}

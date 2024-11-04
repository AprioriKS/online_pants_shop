package com.example.online_pants_shop.repositories.product;

public interface SpecificationProviderManager<T> {
  ProductSpecificationProvider<T> getSpecificationProvider(String key);
}

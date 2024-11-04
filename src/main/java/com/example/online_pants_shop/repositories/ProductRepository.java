package com.example.online_pants_shop.repositories;

import com.example.online_pants_shop.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository <Product, Long>,
    JpaSpecificationExecutor<Product> {
  List<Product> findByCategories_Id(Long categoryId);
  List<Product> findByIsSalesTrue();
  boolean existsByName(String name);
}

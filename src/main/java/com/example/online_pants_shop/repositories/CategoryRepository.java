package com.example.online_pants_shop.repositories;

import com.example.online_pants_shop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  boolean existsByName(String name);

}

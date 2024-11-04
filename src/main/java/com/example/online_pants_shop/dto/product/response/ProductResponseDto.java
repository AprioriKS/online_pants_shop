package com.example.online_pants_shop.dto.product.response;

import java.util.Set;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductResponseDto {
  private Long id;
  private String name;
  private String brand;
  private String color;
  private Set<Long> categoryIds;
  private BigDecimal price;
  private int stock;
  private String description;
  private String imageUrl;
  private boolean isSales;
  private String size;
  private int rating;
  private boolean isDeleted;
  private String style;
}


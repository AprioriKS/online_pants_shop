package com.example.online_pants_shop.dto.category.request;

import lombok.Data;

@Data
public class ProductSearchParametersDto {
  private String category;
  private Double minPrice;
  private Double maxPrice;
  private String size;
  private String color;
  private String brand;
  private Integer rating;
  private int stock;
  private Boolean hasDiscount;
  private String style;
}

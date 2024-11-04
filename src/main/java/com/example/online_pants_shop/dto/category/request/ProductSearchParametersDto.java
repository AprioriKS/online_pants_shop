package com.example.online_pants_shop.dto.category.request;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductSearchParametersDto {
  private String[] categories;
  @Positive
  private BigDecimal fromPrice;
  @Positive
  private BigDecimal toPrice;
  private String[] size;
  private String[] color;
  private String[] brand;
  @Positive
  private int rating;
  @Positive
  private int stock;
  private Boolean isSales;
  private String[] style;
}

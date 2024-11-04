package com.example.online_pants_shop.dto.product.request;

import com.example.online_pants_shop.validation.PathToFile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProductDto {
  @NotBlank
  private String name;
  @NotBlank
  private String brand;
  @NotBlank
  private String color;
  @NotBlank
  private Set<Long> categoryIds = new HashSet<>();
  @NotBlank
  @Positive
  private BigDecimal price;
  @NotBlank
  private int stock;
  @NotBlank
  private String description;
  @NotBlank
  @PathToFile
  private String imageUrl;
  private boolean isSales;
  @NotBlank
  private String size;
  @NotBlank
  @Positive
  private int rating;
  private String style;
}

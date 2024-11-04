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
public class UpdateProductDto {
  private String name;
  private String brand;
  private String color;
  private Set<Long> categoryIds = new HashSet<>();
  @Positive
  private BigDecimal price;
  private Integer stock;
  private String description;
  @NotBlank
  @PathToFile
  private String imageUrl;
  private Boolean isSales;
  private String size;
  @Positive
  private Integer rating;
  private String style;
}

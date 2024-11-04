package com.example.online_pants_shop.dto.category.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateCategoryDto(@NotBlank String name,
                                String description) {}

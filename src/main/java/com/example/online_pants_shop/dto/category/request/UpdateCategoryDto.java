package com.example.online_pants_shop.dto.category.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryDto (@NotBlank String name,
                                 String description) {}

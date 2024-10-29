package com.example.online_pants_shop.dto.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record UserResponseDTO(Long id, String email, String firstName,
                              String lastName, String shippingAddress) {}

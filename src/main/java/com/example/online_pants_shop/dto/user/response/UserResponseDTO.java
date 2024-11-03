package com.example.online_pants_shop.dto.user.response;

public record UserResponseDTO(Long id, String email, String firstName,
                              String lastName, String shippingAddress) {}

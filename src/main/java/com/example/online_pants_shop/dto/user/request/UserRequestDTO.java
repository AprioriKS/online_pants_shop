package com.example.online_pants_shop.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRequestDTO (Long id, String email, String firstName,
                              String lastName, String shippingAddress, String password) {}
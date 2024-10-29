package com.example.online_pants_shop.dto.role.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RoleResponseDTO(Long id, String name) {
}

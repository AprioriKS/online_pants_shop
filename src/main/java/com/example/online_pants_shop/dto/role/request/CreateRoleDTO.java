package com.example.online_pants_shop.dto.role.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateRoleDTO(String name) {}

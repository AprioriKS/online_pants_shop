package com.example.online_pants_shop.mappers;

import com.example.online_pants_shop.config.MapperConfig;
import com.example.online_pants_shop.dto.role.request.RoleRequestDTO;
import com.example.online_pants_shop.dto.role.response.RoleResponseDTO;
import com.example.online_pants_shop.entities.Role;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RoleMapper {
  Role toRole(RoleRequestDTO roleRegistrationRequestDto);

  RoleResponseDTO toRoleResponseDto(Role role);
}

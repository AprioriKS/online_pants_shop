package com.example.online_pants_shop.mappers;

import com.example.online_pants_shop.config.MapperConfig;
import com.example.online_pants_shop.dto.role.request.CreateRoleDTO;
import com.example.online_pants_shop.dto.role.response.RoleResponseDTO;
import com.example.online_pants_shop.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface RoleMapper {
  @Mapping(target = "id", ignore = true)
  Role toRole(CreateRoleDTO roleRegistrationRequestDto);

  RoleResponseDTO toRoleResponseDto(Role role);
}

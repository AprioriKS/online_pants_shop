package com.example.online_pants_shop.mappers;

import com.example.online_pants_shop.config.MapperConfig;
import com.example.online_pants_shop.dto.user.request.CreateUserDTO;
import com.example.online_pants_shop.dto.user.response.UserResponseDTO;
import com.example.online_pants_shop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MapperConfig.class)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  User toUser(CreateUserDTO userRegistrationRequestDto);

  UserResponseDTO toUserResponseDto(User user);
}


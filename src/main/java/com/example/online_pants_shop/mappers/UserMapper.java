package com.example.online_pants_shop.mappers;

import com.example.online_pants_shop.config.MapperConfig;
import com.example.online_pants_shop.dto.user.request.UserRequestDTO;
import com.example.online_pants_shop.dto.user.response.UserResponseDTO;
import com.example.online_pants_shop.entities.User;
import org.mapstruct.Mapper;


@Mapper(config = MapperConfig.class)
public interface UserMapper {
  User toUser(UserRequestDTO userRegistrationRequestDto);

  UserResponseDTO toUserResponseDto(User user);
}


package com.example.online_pants_shop.services;


import com.example.online_pants_shop.dto.user.request.UserRequestDTO;
import com.example.online_pants_shop.dto.user.response.UserResponseDTO;
import com.example.online_pants_shop.entities.User;
import com.example.online_pants_shop.exception.RegistrationException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws RegistrationException;
    Optional<UserResponseDTO> findById(Long id);
    List<UserResponseDTO> getAllUsers(Pageable pageable);
    Optional <User> findByEmail(String email);
    void deleteUser(Long id);
}

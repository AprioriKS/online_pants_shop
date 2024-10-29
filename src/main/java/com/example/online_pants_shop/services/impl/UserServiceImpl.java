package com.example.online_pants_shop.services.impl;

import com.example.online_pants_shop.dto.user.request.UserRequestDTO;
import com.example.online_pants_shop.dto.user.response.UserResponseDTO;
import com.example.online_pants_shop.entities.Role;
import com.example.online_pants_shop.entities.User;
import com.example.online_pants_shop.exception.RegistrationException;
import com.example.online_pants_shop.mappers.UserMapper;
import com.example.online_pants_shop.repositories.RoleRepository;
import com.example.online_pants_shop.repositories.UserRepository;
import com.example.online_pants_shop.services.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws RegistrationException {
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new RegistrationException("User with email "
                + userRequestDTO.email() + " already exists");
        }
        User user = userMapper.toUser(userRequestDTO);
        assignUserRole(user);
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        userRepository.save(user);
        //shoppingCartService.createShoppingCart(user);
        return userMapper.toUserResponseDto(user);
    }

    private void assignUserRole(User user) {
        Role userRole = roleRepository.findByName(Role.RoleName.ROLE_USER)
            .orElseGet(() -> {
                Role newUserRole = new Role();
                newUserRole.setName(Role.RoleName.ROLE_USER);
                return roleRepository.save(newUserRole);
            });
        user.setRoles(Set.of(userRole));
    }



    @Override
    public Optional<UserResponseDTO> findById(Long id) {
        return userRepository.findById(id)
            .map(userMapper::toUserResponseDto);
    }

    @Override
    public List<UserResponseDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll().stream()
            .map(userMapper::toUserResponseDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

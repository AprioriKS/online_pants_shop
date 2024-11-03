package com.example.online_pants_shop.controllers;

import com.example.online_pants_shop.controllers.constant.ShopConstants;
import com.example.online_pants_shop.dto.user.request.CreateUserDTO;
import com.example.online_pants_shop.dto.user.response.UserResponseDTO;
import com.example.online_pants_shop.entities.User;
import com.example.online_pants_shop.exception.RegistrationException;
import com.example.online_pants_shop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "register user", description = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody CreateUserDTO createUserDTO)
        throws RegistrationException {
        UserResponseDTO savedUser = userService.saveUser(createUserDTO);
        return ResponseEntity.ok(savedUser);
    }

    @Operation(summary = "Get all users", description = "Get al users from bd")
    @GetMapping
    public List<UserResponseDTO> getAllUsers(
        @Parameter(example = ShopConstants.PAGEABLE_EXAMPLE_NAME) Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return userService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find user by email", description = "Retrieve a specific user by their email")
    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @Operation(summary = "Delete user", description = "Delete a user by their ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

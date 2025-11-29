package com.hotel.service;

import com.hotel.dto.RegisterRequestDTO;
import com.hotel.dto.UserDTO;
import com.hotel.entity.User;

import java.util.Optional;

public interface UserService {
    UserDTO registerUser(RegisterRequestDTO registerRequest);
    Optional<User> findByEmail(String email);
    UserDTO getUserById(Long id);
    boolean existsByEmail(String email);
}

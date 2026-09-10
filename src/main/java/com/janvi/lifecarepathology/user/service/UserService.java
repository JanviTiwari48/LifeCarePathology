package com.janvi.lifecarepathology.user.service;

import com.janvi.lifecarepathology.user.dto.UserRequest;
import com.janvi.lifecarepathology.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
}
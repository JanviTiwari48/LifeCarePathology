package com.janvi.lifecarepathology.user.mapper;

import com.janvi.lifecarepathology.user.dto.UserRequest;
import com.janvi.lifecarepathology.user.dto.UserResponse;
import com.janvi.lifecarepathology.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // hashed in Phase 8, plain for now
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole());
        response.setEnabled(user.getEnabled());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
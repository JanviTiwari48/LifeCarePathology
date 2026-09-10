package com.janvi.lifecarepathology.user.service.impl;

import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import com.janvi.lifecarepathology.user.dto.UserRequest;
import com.janvi.lifecarepathology.user.dto.UserResponse;
import com.janvi.lifecarepathology.user.entity.User;
import com.janvi.lifecarepathology.user.mapper.UserMapper;
import com.janvi.lifecarepathology.user.repository.UserRepository;
import com.janvi.lifecarepathology.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        existing.setFullName(request.getFullName());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setEmail(request.getEmail());
        existing.setRole(request.getRole());
        User saved = userRepository.save(existing);
        return userMapper.toResponse(saved);
    }

    @Override
    public void deleteUser(Long id) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(existing);
    }
}
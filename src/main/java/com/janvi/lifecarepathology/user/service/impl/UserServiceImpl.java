package com.janvi.lifecarepathology.user.service.impl;

import com.janvi.lifecarepathology.user.entity.User;
import com.janvi.lifecarepathology.user.repository.UserRepository;
import com.janvi.lifecarepathology.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existing = getUserById(id);
        existing.setFullName(updatedUser.getFullName());
        existing.setPhoneNumber(updatedUser.getPhoneNumber());
        existing.setEmail(updatedUser.getEmail());
        existing.setRole(updatedUser.getRole());
        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        User existing = getUserById(id);
        userRepository.delete(existing);
    }
}
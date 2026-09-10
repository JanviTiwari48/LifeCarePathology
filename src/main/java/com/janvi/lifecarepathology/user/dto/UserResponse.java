package com.janvi.lifecarepathology.user.dto;

import com.janvi.lifecarepathology.user.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private Role role;
    private boolean enabled;
    private LocalDateTime createdAt;
    // no password field — this is the entire point
}
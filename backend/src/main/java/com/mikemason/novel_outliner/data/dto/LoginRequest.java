package com.mikemason.novel_outliner.data.dto;

import org.jspecify.annotations.Nullable;

public class LoginRequest {

    private String username;
    private String password;


    // getters and setters
    public @Nullable Object getUsername() {
        return username;
    }

    public @Nullable Object getPassword() {
        return password;
    }
}
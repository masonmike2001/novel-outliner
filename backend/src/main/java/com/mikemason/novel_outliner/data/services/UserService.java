package com.mikemason.novel_outliner.data.services;

import com.mikemason.novel_outliner.data.dto.RegistrationRequest;
import com.mikemason.novel_outliner.data.entities.Authority;
import com.mikemason.novel_outliner.data.entities.User;
import com.mikemason.novel_outliner.data.repositories.AuthorityRepository;
import com.mikemason.novel_outliner.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository,
                       AuthorityRepository authorityRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        Authority authority = new Authority();
        authority.setUser(user);
        authority.setAuthority("ROLE_USER");

        authorityRepository.save(authority);
    }
}


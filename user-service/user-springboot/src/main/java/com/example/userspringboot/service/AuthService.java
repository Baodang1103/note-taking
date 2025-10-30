package com.example.userspringboot.service;

import com.example.userspringboot.dto.RegisterRequest;
import com.example.userspringboot.model.User;
import com.example.userspringboot.repository.IUserrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final IUserrepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(IUserrepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Tai khoan da ton tai");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = new User(request.getUsername(), hashedPassword);

        userRepository.save(newUser);
    }
}

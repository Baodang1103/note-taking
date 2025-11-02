package com.example.userspringboot.service;

import com.example.userspringboot.dto.LoginRequest;
import com.example.userspringboot.dto.RegisterRequest;
import com.example.userspringboot.model.User;
import com.example.userspringboot.repository.IUserrepository;
import com.example.userspringboot.until.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final IUserrepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final IUserrepository iUserrepository;

    @Autowired
    public AuthService(IUserrepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, IUserrepository iUserrepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.iUserrepository = iUserrepository;
    }

    public void register(RegisterRequest request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Tai khoan da ton tai");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = new User(request.getUsername(), hashedPassword);

        userRepository.save(newUser);
    }

    public String login(LoginRequest request){
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("Khong tim thay tai khoan nay!");
        }
        User user = userOptional.get();

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Sai mat khau");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}

package com.example.quotetask1.service;

import com.example.quotetask1.db.Entity.Quote;
import com.example.quotetask1.db.Entity.User;
import com.example.quotetask1.db.Repository.QuoteRepository;
import com.example.quotetask1.db.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public void addUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        password = encoder.encode(password);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }
}

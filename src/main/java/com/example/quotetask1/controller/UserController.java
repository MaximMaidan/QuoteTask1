package com.example.quotetask1.controller;

import com.example.quotetask1.db.Entity.Quote;
import com.example.quotetask1.db.Entity.User;
import com.example.quotetask1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email) {
        userService.addUser(username, password, email);
        return "user added";
    }

    @GetMapping("/user/list")
    public List<User> showAllQuotes() {
        return userService.listAll();
    }

}

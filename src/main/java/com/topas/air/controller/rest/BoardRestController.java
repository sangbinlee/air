package com.topas.air.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topas.air.repository.User;
import com.topas.air.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class BoardRestController {

    private final UserRepository userRepository;

    public BoardRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUsers(@PathVariable Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}

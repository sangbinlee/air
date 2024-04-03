package com.topas.air.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topas.air.repository.oracle.Users;
import com.topas.air.repository.oracle.UserRepository;

@RestController
@RequestMapping("/users")
public class BoardRestController {

    private final UserRepository userRepository;

    public BoardRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Users> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getUsers(@PathVariable Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}

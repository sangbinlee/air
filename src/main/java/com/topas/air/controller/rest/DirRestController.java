package com.topas.air.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topas.air.repository.oracle.Dir;
import com.topas.air.repository.oracle.DirRepository;

@RestController
@RequestMapping("/dir")
public class DirRestController {

    private final DirRepository dirRepository;

    public DirRestController(DirRepository dirRepository) {
        this.dirRepository = dirRepository;
    }

    @GetMapping
    public List<Dir> getUsers() {
        return this.dirRepository.findAll();
    }

    @GetMapping("/{id}")
    public Dir getUsers(@PathVariable Long id) {
        return this.dirRepository.findById(id).orElse(null);
    }
}

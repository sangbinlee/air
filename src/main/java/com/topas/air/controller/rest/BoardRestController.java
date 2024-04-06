package com.topas.air.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topas.air.repository.oracle.Board;
import com.topas.air.repository.oracle.UserRepository;
import com.topas.air.repository.oracle.Users;
import com.topas.air.service.BoardService;

@RestController
@RequestMapping("v1/board")
public class BoardRestController {

	

	@Autowired
	private BoardService boardService;
	
    private final UserRepository userRepository;

    public BoardRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @GetMapping
//    public List<Users> getUsers() {
//        return this.userRepository.findAll();
//    }
    
    @GetMapping
    public List<Board> getUsers() {
    	return boardService.select();
    }

    @GetMapping("/{id}")
    public Users getUsers(@PathVariable Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}

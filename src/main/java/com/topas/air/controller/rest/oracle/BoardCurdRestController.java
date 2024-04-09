package com.topas.air.controller.rest.oracle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topas.air.repository.oracle.RequestUser;
import com.topas.air.repository.oracle.RequestUserRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/boardcurd")
@Tag(name = "boardcurd 컨트롤러", description = "boardcurd API입니다.")
public class BoardCurdRestController {

	@Autowired
	private RequestUserRepository requestUserRepository;
    @GetMapping()
//    @GetMapping("/{id}")
//    @PostMapping()
//    @PutMapping()
//    @DeleteMapping("/{id}")
    public List<RequestUser> getUsers() {
        return requestUserRepository.findAll();
    }
}

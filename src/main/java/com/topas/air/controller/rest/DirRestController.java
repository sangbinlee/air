package com.topas.air.controller.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topas.air.repository.oracle.Dir;
import com.topas.air.repository.oracle.DirRepository;
import com.topas.air.repository.oracle.Files;
import com.topas.air.repository.oracle.FilesRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "QR 컨트롤러", description = "QR코드 API입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/dir")
public class DirRestController {

    private final DirRepository dirRepository;
    private final FilesRepository filesRepository;

//    public DirRestController(DirRepository dirRepository, FilesRepository filesRepository) {
//        this.dirRepository = dirRepository;
//        this.filesRepository = filesRepository;
//    }

//    @GetMapping
//    public List<MenuResult> getV2Menus() {
//    	final List<Dir> all = dirRepository.findAllByParentIsNull();
//    	return all.stream().map(MenuResult::new).collect(Collectors.toList());
//    }

    @GetMapping
    public List<Dir> getUsers() {
    	Optional<Dir> dirList = dirRepository.findById((long) 1);
    	List<Dir> fileList2 =dirList.stream().collect(Collectors.toList());
    	List<Files> fileList = filesRepository.findAll();
    	for (Dir dir : fileList2) {
    		List<Files> list = fileList.stream().filter(x-> x.getId() == dir.getId()).collect(Collectors.toList());
    		dir.setChildren(list);
		}
        return fileList2;
    }

    @GetMapping("/{id}")
    public Dir getUsers(@PathVariable Long id) {
        return this.dirRepository.findById(id).orElse(null);
    }
}

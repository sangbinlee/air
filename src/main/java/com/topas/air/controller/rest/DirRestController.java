package com.topas.air.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topas.air.repository.oracle.Dir;
import com.topas.air.repository.oracle.DirRepository;
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
    public Map<String, Object> getUsers() {
//    	Optional<Dir> dirList = dirRepository.findById((long) 1);
    	List<Dir> dirList = dirRepository.findAll();
//    	List<Dir> fileList2 =dirList.stream().collect(Collectors.toList());
//    	List<Files> fileList = filesRepository.findAll();
//		Set<Dir> result = list.stream().collect(Collectors.toSet());
//		dir.setChildren(result);
		Long menu1 = (long) 1;
		Long menu2 = (long) 2;
		Long menu3 = (long) 3;
		for (Dir dir : dirList) {

		}
		List<Dir> menu1Root = dirList.stream().filter(x-> x.getId() == menu1).collect(Collectors.toList());
		List<Dir> menu2listWithOutRoot = dirList.stream().filter(x-> x.getParent() != null).collect(Collectors.toList());
		List<Dir> menu1list = menu2listWithOutRoot.stream().filter(x-> x.getParent().getId() == menu1).collect(Collectors.toList());
		for (Dir dir : menu1list) {
			Long parentId = dir.getId();
			List<Dir> menu2list = menu2listWithOutRoot.stream().filter(x-> x.getParent().getId() == parentId).collect(Collectors.toList());
			Set<Dir> result = menu2list.stream().collect(Collectors.toSet());
			dir.setChildren(result);
		}
		List<Dir> menu3list = menu2listWithOutRoot.stream().filter(x-> x.getParent().getId() == menu3).collect(Collectors.toList());

//		{
//			  name: 'My Tree',
//			  children: []
//		}
		Map<String, Object> treeMap = new HashMap<String, Object>();
		treeMap.put("name", menu1Root.get(0).getName());
		treeMap.put("children", menu1list);
        return treeMap;
    }

    @GetMapping("/{id}")
    public Dir getUsers(@PathVariable Long id) {
        return this.dirRepository.findById(id).orElse(null);
    }
}

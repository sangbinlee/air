package com.topas.air.controller.rest.oracle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
import com.topas.air.repository.oracle.Board;
import com.topas.air.repository.oracle.BoardRepository;
import com.topas.air.repository.oracle.UserRepository;
import com.topas.air.repository.oracle.Users;
import com.topas.air.service.oracle.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/board")
@Slf4j
public class BoardRestController {



	@Autowired
	private BoardService boardService;


	@Autowired
	private BoardRepository boardRepository;



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
    /**
     * 이 방식은 like 검색 안됨, equal 검색 됨.
     * localhost:8080/v1/board/paging?page=3&size=3&sort=id,desc
     * localhost:8080/v1/board/paging?page=3&size=3&sort=id,desc&sort=name,asc
     * localhost:8080/v1/board/paging?page=0&size=5&sort=id,desc
     * localhost:8080/v1/board/paging?page=0&size=5&sort=id,desc&keyword=name 19
     * @param page
     * @param size
     * @param sort
     * @param pageable
     * @return
     */
    @GetMapping("paging")
    public Page<Board> paging(
    		@RequestParam(name="page",required = false) Integer page
    		, @RequestParam(name="size",required = false) Integer size
    		, @RequestParam(name="sort",required = false) String sort
    		, @RequestParam(name="keyword",required = false) String keyword
    		, @PageableDefault(size = 10) Pageable pageable
    		) {

    	log.info("page={}", page);
    	log.info("size={}", size);
    	log.info("sort={}", sort);
    	log.info("keyword={}", keyword);
    	log.info("pageable={}", pageable);// Page request [number: 3, size 3, sort: name: DESC]




//    	  for (long i = 0; i < 99; i++) {
//    		  Board board = new Board( i,  "name " + i  );
//    		  boardRepository.save(board);
//    	  }

    	  Board prototype = new Board();
    	  prototype.setName(keyword);

    	  Example<Board> example = Example.of(prototype);
//    			  , matching().withIgnoreCase().withIgnorePaths("name")
////    			  .withIgnoreCase().withIgnorePaths("name", "createdAt")
//    			  .withStringMatcher(StringMatcher.STARTING).withIgnoreCase());



//    	Page<Board> list = boardRepository.findAll(pageable);
    	Page<Board> list = boardRepository.findAll(example, pageable);

//		if (page == null) {
//			page = 1;
//		}
//		if (size == null) {
//			size = 10;// pageSize
//		}

//		PageHelper.startPage(page, size);

//		Page<Board> list = boardService.paging();

    	return list;
    }

    @GetMapping("/{id}")
    public Users getUsers(@PathVariable Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}

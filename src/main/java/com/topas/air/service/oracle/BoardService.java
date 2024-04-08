package com.topas.air.service.oracle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.topas.air.mapper.oracle.BoardMapper;
import com.topas.air.repository.oracle.Board;

@Service
public class BoardService {

	@Autowired
	BoardMapper boardMapper;

	public List<Board> select() {
		return boardMapper.select();

	}

	public Page<Board> paging() {
		return boardMapper.paging();

	}

}

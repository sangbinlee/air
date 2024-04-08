package com.topas.air.mapper.oracle;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.topas.air.repository.oracle.Board;

@Mapper
public interface BoardMapper {

	List<Board> select();

	Page<Board> paging();

	Board selectOne(int myno);

	int insert(Board dto);

	int update(Board dto);

	int delete(int myno);

}

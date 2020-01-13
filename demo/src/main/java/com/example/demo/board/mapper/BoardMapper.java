package com.example.demo.board.mapper;

import org.springframework.stereotype.Repository;

@Repository("com.example.demo.board.mapper.BoardMapper")
public interface BoardMapper {
	
	public int boardCount() throws Exception;

}	

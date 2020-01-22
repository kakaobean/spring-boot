package com.example.demo.board.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("com.example.demo.board.mapper.BoardMapper")
public interface BoardMapper {
	
	public int boardCount() throws Exception;

	public String stringTest() throws Exception;
	
	public List selectResidentPopulList() throws Exception;
	
	public List<Map<String, Object>> selectDynamicData() throws Exception;

}	

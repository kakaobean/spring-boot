package com.example.demo.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
	public int getBoardCount() throws Exception;

	public Map<String, Object> selectResidentPopulList() throws Exception;
	
	public Map<String, Object> selectDynamicData() throws Exception;
}

package com.example.demo.board.service;

import java.util.List;

public interface BoardService {
	public int getBoardCount() throws Exception;

	public List selectResidentPopulList() throws Exception;
	
	public List selectDynamicData() throws Exception;
}

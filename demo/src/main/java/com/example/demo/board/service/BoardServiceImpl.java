package com.example.demo.board.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

//	@Resource(name="com.example.demo.board.mapper.BoardMapper")
	@Autowired
	private BoardMapper mBoardMapper;

	@Override
	public int getBoardCount() throws Exception { 
		return mBoardMapper.boardCount();
	}
	
//	System.out.println(mBoardMapper.boardCount());
}

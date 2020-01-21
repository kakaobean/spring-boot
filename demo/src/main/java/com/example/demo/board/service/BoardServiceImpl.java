package com.example.demo.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

//	@Autowired
	@Resource(name="com.example.demo.board.mapper.BoardMapper")
	private BoardMapper mBoardMapper;

	@Override
	public int getBoardCount() throws Exception {
		System.out.println("================"+ mBoardMapper.boardCount());
		System.out.println("================"+ mBoardMapper.selectResidentPopulList());
//		System.out.println("================"+ mBoardMapper.stringTest());
		return mBoardMapper.boardCount();
		
	}
	@Override
	public List selectResidentPopulList() throws Exception {
		List m = mBoardMapper.selectResidentPopulList();
		String str = m.get(0).toString();
		System.out.println(str);
		return mBoardMapper.selectResidentPopulList();
		
	}
	@Override
	public List selectDynamicData() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		return null;
		
	}
	
//	System.out.println(mBoardMapper.boardCount());
}

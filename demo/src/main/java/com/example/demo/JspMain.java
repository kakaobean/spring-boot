package com.example.demo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.board.mapper.BoardMapper;

@Controller
public class JspMain {
	
	@Resource(name="com.example.demo.board.mapper.BoardMapper")
	BoardMapper mBoardMapper;
	
	@RequestMapping("/main")
	public String jspMain() throws Exception{
//		System.out.println(mBoardMapper.boardCount());
		return "main";
	}
}

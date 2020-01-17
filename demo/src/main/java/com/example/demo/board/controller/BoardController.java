package com.example.demo.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.board.service.BoardService;

@Controller
public class BoardController {
	
//	@Resource(name="com.example.demo.board.service.BoardService")
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/main2")
	public String main(Model model) throws Exception{
		System.out.println("========"+boardService.getBoardCount());
		
		model.addAttribute("test", "sample");
		return "main";
	}
	
	@RequestMapping(value="/getTest", method= RequestMethod.POST)
		public @ResponseBody Map<String, Object> getTest() throws Exception{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("testCount", boardService.getBoardCount());
			
			return map;
		}
	}


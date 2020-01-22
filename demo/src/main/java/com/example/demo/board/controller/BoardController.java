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
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
	@RequestMapping("/dashboardFinance")
	public String dashboardFinance() {
		return "dashboard-finance";
	}
	@RequestMapping("/dashboardInfluencer")
	public String dashboardInfluencer() {
		return "dashboard-influencer";
	}
	@RequestMapping("/dashboardSales")
	public String dashboardSales() {
		return "dashboard-sales";
	}
	@RequestMapping("/ecommerceProductCheckout")
	public String ecommerceProductCheckout() {
		return "ecommerce-product-checkout";
	}
	@RequestMapping("/ecommerceProductSingle")
	public String ecommerceProductSingle() {
		return "ecommerce-product-single";
	}
	@RequestMapping("/ecommerceProduct")
	public String ecommerceProduct() {
		return "ecommerceProduct";
	}
	@RequestMapping("/influencerFinder")
	public String influencerFinder() {
		return "influencer-finder";
	}
	@RequestMapping("/influencerProfile")
	public String influencerProfile() {
		return "influencer-profile";
	}
	
	@RequestMapping(value="/getTest", method= RequestMethod.POST)
	// @ResponseBody 위로 올려서 return 되는지 테스트 해볼 것
		public @ResponseBody Map<String, Object> getTest() throws Exception{
			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("testCount", boardService.getBoardCount());
//			map.put("residentPopulList", boardService.selectResidentPopulList());
			// 동적 테스트
//			map.put("dynamic", boardService.selectDynamicData());
			return boardService.selectDynamicData();
		}
	}


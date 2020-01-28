package com.example.demo.board.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.couchbase.client.java.document.json.JsonArray;
import com.example.demo.board.domain.parameterVO;
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
	@RequestMapping("/noTiles")
	public String noTiles() {
		return "tiles/noTiles";
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
			
			// 주민등록 인구 리스트
			return boardService.selectResidentPopulList();
			
			// 동적 컬럼 데이터 리스트
//			return boardService.selectDynamicData();
		}
	@RequestMapping(value="/uploadTest", method= RequestMethod.POST)
	public @ResponseBody String uploadTest(MultipartHttpServletRequest req) throws Exception{
		
//		Iterator<String> iter = req.getFileNames();
//		MultipartFile files = req.getFile(iter.next());
		MultipartFile file = req.getFile("testFile");
		System.out.println(file.getOriginalFilename());
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/excelDownload", method= RequestMethod.POST)
	public Map<String, Object> excelDownload(@RequestBody Map<String, Object> map) throws Exception{
//		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(map.get("getRowData"));
		map.put("data", "test");
		return map;
	}
	
}


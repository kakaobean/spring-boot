package com.example.demo.board.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public @ResponseBody Map<String, Object> uploadTest(MultipartHttpServletRequest req) throws Exception{
		
//		Iterator<String> iter = req.getFileNames();
//		MultipartFile files = req.getFile(iter.next());
		MultipartFile file = req.getFile("inputFile");
		
		return boardService.excelRead(file);
	}
	
	@ResponseBody
	@RequestMapping(value="/excelDownload", method= RequestMethod.POST)
	public String excelDownload(@RequestBody Map<String, Object> map,
											HttpServletResponse response) throws Exception{
		
		System.out.println(map);
		
		Workbook wb = boardService.createWorkbook(map);
		
		 // 컨텐츠 타입과 파일명 지정
	    response.setContentType("ms-vnd/excel");
//		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
	    response.setHeader("Content-Disposition", "attachment;filename=test123.xls");

//	    FileOutputStream fileOut = new FileOutputStream("D:/work.xls");
//	    wb.write(fileOut);
	    // 엑셀 출력	
	    wb.write(response.getOutputStream());
	    wb.close();
		
		return "success";
	}
		
	
	 // 현재 쓰이고 있는 엑셀다운로드
	 @RequestMapping(value = "/excelDown", method = RequestMethod.POST)
	    public void ExcelDown(HttpServletResponse response) throws Exception{
	        
		  Map<String, Object> map = new HashMap<String, Object>();
	        
//	        try{
	            //Excel Down 시작
	        	Workbook workbook = boardService.createWb();
	        	
	            // 컨텐츠 타입과 파일명 지정
	            response.setContentType("ms-vnd/excel");
	            response.setHeader("Content-Disposition", "attachment;filename=test.xls");
	 
	            // 엑셀 출력
	            workbook.write(response.getOutputStream());
	            workbook.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }


	        
	    }

}


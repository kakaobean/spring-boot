package com.example.demo.board.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
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
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

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
	public Map<String, Object> selectResidentPopulList() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = mBoardMapper.selectResidentPopulList();
		Map<String, Object> mapTemp = list.get(0);
		map.put("columnList", mapTemp.keySet());
		map.put("dataList", list);
		return map;
	}
	@Override
	public Map<String, Object> selectDynamicData() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapTemp = new HashMap<String, Object>();
		System.out.println("===== "+mBoardMapper.selectDynamicData());
		List<Map<String, Object>> list = mBoardMapper.selectDynamicData();
		List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
		List rowCount = new ArrayList<String>();
		List column = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++){
			if(!rowCount.contains(list.get(i).get("ITEMROW"))){
				rowCount.add(list.get(i).get("ITEMROW"));
			}
		}
//		System.out.println(rowCount);
//		System.out.println(rowCount.size());
//		String[] column =  {"block", "col0", "col1", "col2", "col3", "col4", "col5"};
		/**
		 * Map< column, Object>
		 */
		if(((BigDecimal)list.get(0).get("ITEMROW")).intValue() == 0){
			
			System.out.println("ITEMROW2 : " + ((BigDecimal)list.get(0).get("ITEMROW")).intValue());
		}
//		System.out.println("ITEMROW : " + list.get(0).get("ITEMROW"));
		for(int i = 0; i < rowCount.size(); i++){
			mapTemp = new HashMap<String, Object>();
			for(int j = 0; j < list.size(); j++){
				if(!column.contains(list.get(j).get("ITEM"))){
					column.add(list.get(j).get("ITEM"));
				}
				if(((BigDecimal)list.get(j).get("ITEMROW")).intValue() == i){
					mapTemp.put((String) list.get(j).get("ITEM"), (String) list.get(j).get("VALUE"));
				}
			}
			listTemp.add(mapTemp);
		}
		System.out.println("column : "+column);
		System.out.println(mapTemp.keySet());
//		System.out.println("mapTemp : "+ mapTemp);
//		System.out.println("listTemp : "+ listTemp);
		map.put("columnList", mapTemp.keySet());
		map.put("dataList", listTemp);
		return map;
		
	}
	@Override
	public Workbook createWorkbook(Map<String, Object> map) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("getRowData");
		Map<String, Object> keyValSet = list.get(0);
		Set<String> keySet = keyValSet.keySet();
		System.out.println(keySet);
		for(int i = 0; i < list.size(); i++){
//			System.out.println(list.get(i));
		}
		// 워크북 생성

	    Workbook wb = new HSSFWorkbook();
	    
	    Sheet sheet = wb.createSheet("게시판");
	    Row row = null;
	    Cell cell = null;
	    int rowNo = 0;
	    
	    // 테이블 헤더용 스타일

	    CellStyle headStyle = wb.createCellStyle();

	    // 가는 경계선을 가집니다.
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);
	    
	    // 배경색은 노란색입니다.
	    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // 데이터는 가운데 정렬합니다.
	    headStyle.setAlignment(HorizontalAlignment.CENTER);

	    // 데이터용 경계 스타일 테두리만 지정

	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);

	    // 헤더 생성

	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("번호");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("이름");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("제목");

	    // 데이터 부분 생성
	    for(Map<String, Object> mapData : list) {
	        row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue("test1");
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue("test2");
//	        cell.setCellValue((double) mapData.get("HOUSEPOPUL"));
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue("test3");
//	        cell.setCellValue((double) mapData.get("FOREIGNM"));
	    }

	   return wb;
	}
	
	/* (non-Javadoc)
	 * @see com.example.demo.board.service.BoardService#createWb()
	 */
	/* (non-Javadoc)
	 * @see com.example.demo.board.service.BoardService#createWb()
	 */
	@Override
	public Workbook createWb() throws Exception {
		 Workbook workbook = new HSSFWorkbook();  // xls 버전
         //시트생성
         Sheet sheet = workbook.createSheet("게시판");
         
         //행, 열, 열번호
         Row row = null;
         Cell cell = null;
         int rowNo = 0;
         
         // 테이블 헤더용 스타일
         CellStyle headStyle = workbook.createCellStyle();
 
         // 가는 경계선을 가집니다.
         headStyle.setBorderTop(BorderStyle.THIN);
         headStyle.setBorderBottom(BorderStyle.THIN);
         headStyle.setBorderLeft(BorderStyle.THIN);
         headStyle.setBorderRight(BorderStyle.THIN);
 
         // 배경색은 노란색입니다.
         headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
         headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
 
         // 데이터는 가운데 정렬합니다.
         headStyle.setAlignment(HorizontalAlignment.CENTER);
 
         // 데이터용 경계 스타일 테두리만 지정
         CellStyle bodyStyle = workbook.createCellStyle();
         bodyStyle.setBorderTop(BorderStyle.THIN);
         bodyStyle.setBorderBottom(BorderStyle.THIN);
         bodyStyle.setBorderLeft(BorderStyle.THIN);
         bodyStyle.setBorderRight(BorderStyle.THIN);
 
         // 헤더 생성  // keySet을 만들어서  아래 배열이 필요없음
//         String colSet[] = {"BASEDATE" ,"SGGNM" ,"NATIVEM" ,"NATIVEF" ,"FOREIGNF" ,"FOREIGNM" ,"HOUSEPOPUL" ,"SENIORCITIZ"};
         
         Map<String, Object> map = new HashMap<String, Object>();
         List<Map<String, Object>> list = mBoardMapper.selectResidentPopulList();
         Map<String, Object> mapTemp = list.get(0);
         
         Set<String> keySet = mapTemp.keySet();
         
         row = sheet.createRow(rowNo++);
         int colNum = 0;
 
         for(String key : keySet){
        	 cell = row.createCell(colNum);
        	 cell.setCellStyle(headStyle);
        	 cell.setCellValue(key);
        	 colNum++;
         }
    	 // 컬럼 하드코딩시 사용가능
//         for(int i = 0; i < colSet.length; i++){
//        	 cell = row.createCell(i);
//        	 cell.setCellStyle(headStyle);
//        	 cell.setCellValue(colSet[i]);
//         }
         
         // 데이터 부분 생성
         for(int i = 0; i < list.size(); i++){
        	 row = sheet.createRow(i+1);
        	 colNum = 0;
        	 
        	 for(String key : keySet){
        		 cell = row.createCell(colNum);
        		 cell.setCellStyle(bodyStyle);
        		 cell.setCellValue( list.get(i).get(key).toString() );   // Map안에 String, integer가 섞여있기때문에   value값에  .toString() 을 붙여준다.
        		 colNum++;
        	 }
        	 
        	 // 컬럼 하드코딩시 사용가능
//        	 for(int j = 0; j < colSet.length; j++){
//        		 cell = row.createCell(j);
//        		 cell.setCellStyle(bodyStyle);
//        		 cell.setCellValue( list.get(i).get(colSet[j]).toString() );
//        	 }
         }
		 return workbook;
	}
	@Override
	public Map<String, Object> excelRead(MultipartFile file) throws Exception {
		
//		FileInputStream fis = new FileInputStream("D:/test.xls");
		
		Workbook workbook = new HSSFWorkbook(file.getInputStream());  // xls 버전
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		
		// Map 담기 위한 준비
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapTemp = new HashMap<String, Object>();
		List<String> columnList = new ArrayList<String>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		for(int s = 0; s < workbook.getNumberOfSheets(); s++){
			sheet = workbook.getSheetAt(s);
			
			for(int i = 0; i < sheet.getPhysicalNumberOfRows(); i++){
				row = sheet.getRow(i);
				
				// 헤더정보 입력
				if(i == 0){
					for(int j = 0; j < row.getPhysicalNumberOfCells(); j++){
						cell = row.getCell(j);
						columnList.add(cell.getStringCellValue());  // String으로 변환하지 않고  Cell타입으로 그대로 받으면 ajax 통신이 안됨 에러남.
					}
				}else{
					mapTemp = new HashMap<String, Object>();
					
					for(int j = 0; j < row.getPhysicalNumberOfCells(); j++){
						cell = row.getCell(j);
						mapTemp.put(columnList.get(j), cell.getStringCellValue());
					}
					
					dataList.add(mapTemp);
				}
			}
			map.put("columnList", columnList);
			map.put("dataList", dataList);
		}
		
		return map;
	}
}

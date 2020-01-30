package com.example.demo.board.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
	public int getBoardCount() throws Exception;

	public Map<String, Object> selectResidentPopulList() throws Exception;
	
	public Map<String, Object> selectDynamicData() throws Exception;

	Workbook createWorkbook(Map<String, Object> map) throws Exception;

	Workbook createWb() throws Exception;
	
	// 엑셀 파일 내용 읽기
	public Map<String, Object> excelRead(MultipartFile file) throws Exception;
}

package com.example.demo.board.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
}

package com.buskstop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buskstop.dao.PerformanceCommentDao;
import com.buskstop.service.PerformanceCommentService;
import com.buskstop.vo.Performance;
import com.buskstop.vo.PerformanceComment;

@Service
public class PerformanceCommentServiceImpl implements PerformanceCommentService{
	
	@Autowired
	private PerformanceCommentDao dao;
	
	@Override
	public List<PerformanceComment> listComment(int performanceNo){
		return dao.listComment(performanceNo);
	}
	
	@Override
	public void insertPerformanceComment(PerformanceComment performanceComment) {
		dao.insertPerformanceComment(performanceComment);
	}
	
	@Override
	public int deletePerformanceCommentByPerformanceCommentNo(int performanceCommentNo) {
		return dao.deletePerformanceCommentByPerformanceCommentNo(performanceCommentNo);
	}
	
	@Override
	public void updatePerformanceComment(PerformanceComment performanceComment) {
		dao.updatePerformanceComment(performanceComment);
	}

	@Override
	public List<PerformanceComment> searchPerformanceComment(int performanceNo, String userId, String content) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("performanceNo", performanceNo);
		map.put("userId", userId);
		map.put("content", content);
		
		return dao.selectPerformanceComment(map);
		
	}
	
	@Override
	public void deletePerformanceComment(int performanceCommentNo){
		dao.deletePerformanceCommentByPerformanceCommentNo(performanceCommentNo);
	}
	
}

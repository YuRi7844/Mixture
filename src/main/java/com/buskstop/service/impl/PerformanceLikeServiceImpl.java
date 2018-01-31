package com.buskstop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buskstop.dao.PerformanceLikeDao;
import com.buskstop.service.PerformanceLikeService;
import com.buskstop.vo.Performance;
import com.buskstop.vo.PerformanceLike;

@Service
public class PerformanceLikeServiceImpl implements PerformanceLikeService{
	
	@Autowired
	private PerformanceLikeDao dao;
	
	// 공연정보 좋아요 관련
	@Override
	public void insertPerformanceLike(PerformanceLike like) {
		dao.insertPerformanceLike(like);
	}
	
	@Override
	public void deletePerformanceLike(PerformanceLike like) {
		dao.deletePerformanceLike(like);
	}
	
	@Override
	public List<PerformanceLike> selectperformanceLikeByPerformanceLikeNo(int num){
		return dao.selectperformanceLikeByPerformanceLikeNo(num);
	}
	
	@Override
	public int countPerformanceLikeByPerformanceLikeNo(int num) {
		return dao.countPerformanceLikeByPerformanceLikeNo(num);
	}
	
	@Override
	public List<PerformanceLike> selectAllPerformanceLike(){
		return dao.selectAllPerformanceLike();
	}

	@Override
	public List<Performance> performanceByLikeId(String id) {
		return dao.selectPerformanceByLikeId(id);
	}

}

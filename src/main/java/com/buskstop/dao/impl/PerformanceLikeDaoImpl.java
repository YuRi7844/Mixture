package com.buskstop.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.PerformanceLikeDao;
import com.buskstop.vo.Performance;
import com.buskstop.vo.PerformanceLike;

@Repository
public class PerformanceLikeDaoImpl implements PerformanceLikeDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.performanceLikeMapper."+id;
	}
	
	@Override
	public int insertPerformanceLike(PerformanceLike like) {
		return session.insert(makeSqlId("insertPerformanceLike"), like);
	}
	
	@Override
	public int deletePerformanceLike(PerformanceLike like) {
		return session.delete(makeSqlId("deletePerformanceLike"), like);
	}
	
	@Override
	public List<PerformanceLike> selectperformanceLikeByPerformanceLikeNo(int num){
		return session.selectList(makeSqlId("selectperformanceLikeByPerformanceLikeNo"), num);
	}
	
	@Override
	public int countPerformanceLikeByPerformanceLikeNo(int num) {
		return session.selectOne(makeSqlId("countPerformanceLikeByPerformanceLikeNo"), num);
	}
	
	@Override
	public List<PerformanceLike>selectAllPerformanceLike(){
		return session.selectList(makeSqlId("selectAllPerformanceLike"));
	}

	@Override
	public List<Performance> selectPerformanceByLikeId(String id) {
		return session.selectList(makeSqlId("selectPerformanceByLikeId"), id );
	}
	
	
}

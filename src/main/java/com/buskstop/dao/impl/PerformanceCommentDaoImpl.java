package com.buskstop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.PerformanceCommentDao;
import com.buskstop.vo.PerformanceComment;

@Repository
public class PerformanceCommentDaoImpl implements PerformanceCommentDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSql(String id) {
		return "com.buskstop.config.mybatis.mapper.performanceCommentMapper."+id;
	}
	
	@Override
	public List<PerformanceComment> listComment(int performanceNo){
//		System.out.println("dao까지는 오냐");
		return session.selectList(makeSql("listComment"),performanceNo);
	}
	
	@Override
	public int insertPerformanceComment(PerformanceComment performanceComment) {
			return session.insert(makeSql("insertPerformanceComment"),performanceComment);
	}
	
	@Override
	public int updatePerformanceComment(PerformanceComment performanceComment) {
//		System.out.println("dao"+performanceComment.getPerformanceComment());
//		System.out.println(session.update(makeSql("updatePerformanceComment"),performanceComment));
		return session.update(makeSql("updatePerformanceComment"),performanceComment);
	}
	
	@Override
	public int deletePerformanceCommentByPerformanceCommentNo(int performanceCommentNo) {
//		System.out.println("dao까지");
//		System.out.println(session.delete(makeSql("deletePerformanceCommentByPerformanceCommentNo"),performanceCommentNo));
		return session.delete(makeSql("deletePerformanceCommentByPerformanceCommentNo"),performanceCommentNo);
	}

	@Override
	public List<PerformanceComment> selectPerformanceComment(HashMap<String, Object> map) {
		return session.selectList(makeSql("selectPerformanceComment"), map);
	}
	
}

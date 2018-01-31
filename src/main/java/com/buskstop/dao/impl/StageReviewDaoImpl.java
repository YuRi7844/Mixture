package com.buskstop.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.StageReviewDao;
import com.buskstop.vo.Stage;
import com.buskstop.vo.StageReview;

@Repository
public class StageReviewDaoImpl implements StageReviewDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSql(String id) {
		return "com.buskstop.config.mybatis.mapper.stageReviewMapper."+id;
	}

	@Override
	public List<StageReview> listComment(int stageNo) {
		return session.selectList(makeSql("listComment"),stageNo);
	}

	@Override
	public int insertStageComment(StageReview sComment) {
		return session.insert(makeSql("insertStageComment"),sComment);
	}

	@Override
	public int updateStageComment(StageReview sComment2) {
		return session.update(makeSql("updateStageComment"),sComment2);
	}

	@Override
	public int deleteStageCommentByStageCommentUserId(String stageCommentUserId) {
		return session.delete(makeSql("deleteStageCommentByStageCommentUserId"),stageCommentUserId);
	}
	
	
}

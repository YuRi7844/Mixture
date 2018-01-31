package com.buskstop.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.StageImageDao;
import com.buskstop.vo.StageImage;

@Repository
public class StageImageDaoImpl implements StageImageDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.stageImageMapper."+id;
	}
	
	@Override
	public int insertStageImage(StageImage stageImage) {
		return session.insert(makeSqlId("insertStageImage"), stageImage);
	}
	
	@Override
	public int deleteStageImageByStageNo(int stageNo) {
		return session.delete(makeSqlId("deleteStageImageByStageNo"), stageNo);
	}
	
	@Override
	public List<StageImage> selectStageImageByStageNo(int stageNo) {
		return session.selectList(makeSqlId("selectStageImageByStageNo"), stageNo);
	}
	
	@Override
	public int deleteStageImageByStageImageNo(int stageImageNo) {
		return session.delete(makeSqlId("deleteStageImageByStageImageNo"), stageImageNo);
	}
}

package com.buskstop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.VideoCommentDao;
import com.buskstop.vo.VideoComment;

@Repository
public class VideoCommentDaoImpl implements VideoCommentDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.videoCommentMapper."+id;
	}
	
	@Override
	public int insertVideoComment(VideoComment videoComment) {
		return session.insert(makeSqlId("insertVideoComment"), videoComment);
	}

	@Override
	public List<VideoComment> selectVideoCommentByVideoNo(int videoNo) {
		return session.selectList(makeSqlId("selectVideoCommentByVideoNo"),videoNo);
	}

	@Override
	public int updateVideoCommentByVideoCommentNo(VideoComment videoComment) {
		return session.update(makeSqlId("updateVideoCommentByVideoCommentNo"), videoComment);
	}

	@Override
	public int deleteVideoCommentByVideoCommentNo(int videoCommentNo) {
		return session.delete(makeSqlId("deleteVideoCommentByVideoCommentNo"), videoCommentNo);
	}

	@Override
	public List<VideoComment> selectVideoCommentByMap(HashMap<String, Object> map) {
		return session.selectList(makeSqlId("selectVideoCommentByMap"), map);
	}

	@Override
	public int deleteVideoCommentByVideoNo(int videoNo) {
		return session.delete(makeSqlId("deleteVideoCommentByVideoNo"), videoNo);
	}
	
}

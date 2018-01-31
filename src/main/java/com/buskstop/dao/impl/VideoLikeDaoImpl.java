package com.buskstop.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.VideoLikeDao;
import com.buskstop.vo.Video;
import com.buskstop.vo.VideoLike;


@Repository
public class VideoLikeDaoImpl implements VideoLikeDao {

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int insertVideoLike(VideoLike like) {
		return session.insert(makeSqlId("insertVideoLike"),like);
	}

	@Override
	public int deleteVideoLike(VideoLike like) {
		return session.delete(makeSqlId("deleteVideoLike"),like);
	}
	
	@Override
	public List<VideoLike> selectLikeUserByVideoNum(int videoNum) {
		return session.selectList(makeSqlId("selectLikeUserByVideoNum"), videoNum);
	}
	
	@Override
	public List<Video> selectVideoByVideoLikeId(String id){
		return session.selectList(makeSqlId("selectVideoByVideoLikeId"), id);
	}
	
	@Override
	public int deleteVideoLikeByVideoNo(int videoNo) {
		return session.delete(makeSqlId("deleteVideoLikeByVideoNo"), videoNo);
	}

	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.videoLikeMapper."+id;
	}
	
}

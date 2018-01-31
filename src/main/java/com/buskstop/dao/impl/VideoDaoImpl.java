package com.buskstop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.VideoDao;
import com.buskstop.vo.Video;

@Repository
public class VideoDaoImpl implements VideoDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.videoMapper."+id;
	}

	@Override//동영상 등록
	public int insertVideo(Video video) {
		return session.insert(makeSqlId("insertVideo"), video);
	}
	
	@Override//동영상 번호로 동영상 조회
	public Video selectVideoByVideoNo(int videoNo) {
		return session.selectOne(makeSqlId("selectVideoByVideoNo"), videoNo);
	}

	@Override//영상 수정
	public int updateVideo(Video video) {
		return session.update(makeSqlId("updateVideo"), video);
	}

	@Override//영상 삭제
	public int deleteVideoByVideoNum(int videoNo) {
		return session.delete(makeSqlId("deleteVideoByVideoNum"), videoNo);
	}

	
	/****영상 조회*****/
	
	//카테고리로 영상 조회
	@Override 
	public List<Video> selectAllVideoByCategory(String category) {
		return session.selectList(makeSqlId("selectAllVideoByCategory"),category);
	}

	//제목으로 영상 조회
	@Override
	public List<Video> selectVideoByTitleAndCategory(String videoCategory, String videoTitle) {
		HashMap<String, String> map = new HashMap<>();
		map.put("videoCategory", videoCategory);
		map.put("videoTitle", videoTitle);
		return session.selectList(makeSqlId("selectVideoByTitleAndCategory"), map);
	}

	//아티스트로 영상 조회
	@Override
	public List<Video> selectVideoByArtistAndCategory(String videoCategory, String videoArtist) {
		HashMap<String, String>map = new HashMap<>();
		map.put("videoCategory", videoCategory);
		map.put("videoArtist", videoArtist);
		return session.selectList(makeSqlId("selectVideoByArtistAndCategory"), map);
	}

	//게시자 아이디로 영상 조회
	@Override
	public List<Video> selectVideoByUserIdAndCategory(String videoCategory, String videoUserId) {
		HashMap<String, String> map = new HashMap<>();
		map.put("videoCategory", videoCategory);
		map.put("videoUserId", videoUserId);
		return session.selectList(makeSqlId("selectVideoByUserIdAndCategory"), map);
	}
	
	//게시글 추가 입력내용으로 영상 조회
	@Override
	public List<Video> selectVideoByContentAndCategory(String videoCategory, String videoContent) {
		HashMap<String, String> map = new HashMap<>();
		map.put("videoCategory", videoCategory);
		map.put("videoContent", videoContent);
		return session.selectList(makeSqlId("selectVideoByContentAndCategory"), map);
	}

	@Override
	public List<Video> selectVideo() {
		return session.selectList(makeSqlId("selectVideo"));
	}

	@Override
	public List<Video> selectVideoByArtistId(String videoArtist) {
		return session.selectList(makeSqlId("selectVideoByArtistId"),videoArtist);
	}
	
	public List<Video> selectVideoByLikeCount() {
		return session.selectList(makeSqlId("selectVideoByLikeCount"));
	}

	@Override
	public int selectUserVideoCount(String userId) {
		return session.selectOne(makeSqlId("selectUserVideoCount"), userId);
	}
	@Override
	public List<Video> selectMyVideo(String videoUserId){
		return session.selectList(makeSqlId("selectMyVideo"), videoUserId);
	}
}

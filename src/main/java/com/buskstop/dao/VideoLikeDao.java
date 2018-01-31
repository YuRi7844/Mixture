package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.Video;
import com.buskstop.vo.VideoLike;

public interface VideoLikeDao {
	/**
	 * 좋아요 테이블에 데이터 저장
	 * @param like
	 * @return
	 */
	int insertVideoLike(VideoLike like);
	
	/**
	 * 좋아요 데이터 삭제
	 * @param userId
	 * @return
	 */
	int deleteVideoLike(VideoLike like);
	
	List<VideoLike> selectLikeUserByVideoNum(int num);
	/**
	 * 유저 id로 video list 데이터 조회
	 * @param id
	 * @return
	 */
	public List<Video> selectVideoByVideoLikeId(String id);
	
	/**
	 * videoNo 를 받아서 전체 VideoLike 정보를 delete
	 * @param videoNo
	 * @return
	 */
	int deleteVideoLikeByVideoNo(int videoNo);
}

package com.buskstop.service;

import java.util.List;
import com.buskstop.vo.Video;
import com.buskstop.vo.VideoLike;

public interface VideoService {
	
	/* ####### 좋아요 ########*/
	/**
	 * 좋아요 정보를 저장
	 * @param like
	 */
	public void plusLike(VideoLike like);
	/**
	 * 좋아요 정보 삭제
	 * @param like
	 */
	public void deleteLike(VideoLike like);
	/**
	 * 좋아요누른 유저정보 조회
	 * @param num
	 * @return
	 */
	public List<VideoLike> selectLikeUserByNum(int num);
	/**
	 * 유저가 누른 좋아요 영상목록 조회.
	 * @param id
	 * @return
	 */
	public List<Video> selectVideoByVideoLikeId(String id);
	
	/*######## 동영상 ##########*/
	/**
	 * 동영상 등록
	 * @param video
	 */
	int insertVideo(Video video);
	
	/**
	 * 동영상의 정보를 수정한다.
	 * @param video
	 * @return
	 */
	int updateVideo(Video video);
	
	/**
	 * 영상번호를 받아 동영상 정보를 제거한다.
	 * @param videoNo
	 * @return
	 */
	int deleteVideoByVideoNum(int videoNo);
	
	/* -------- 동영상 조회 ----------*/
	
	/**
	 * 모든 공연영상을 조회.
	 * @return
	 */
	List<Video> viewAllVideo(String category);
	
	/**
	 * 
	 * 동영상 번호로 동영상 조회
	 * @param videoNo
	 * @return
	 */
	Video viewVideoByVideoNo(int videoNo);
	
	/**
	 * 제목으로 영상 조회
	 * @param videoCategory
	 * @param videoTitle
	 * @return
	 */
	List<Video> viewVideoByTitleAndCategory(String videoCategory, String videoTitle);
	
	/**
	 * 게시자 id로 영상조회
	 * @param videoCategory
	 * @param videoArtist
	 * @return
	 */
	List<Video> viewVideoByArtistAndCategory(String videoCategory, String videoArtist);
	
	/**
	 * 아티스트명으로 영상조회
	 * @param videoCategory
	 * @param vodeUserId
	 * @return
	 */
	List<Video> viewVideoByUserIdAndCategory(String videoCategory, String videoUserId);
	
	/**
	 * 내용(추가글)으로 영상조회
	 * @param videoCategory
	 * @param videoContent
	 * @return
	 */
	List<Video> viewVideoByContentAndCategory(String videoCategory, String videoContent);
	
	/**
	 * video 정보 삭제 후 category 반환해주는 service.
	 * @param videoNo
	 * @return
	 */
	List<Video> deleteVideoAndSelect(int videoNo);
	
	/**
	 * 모든 video 정보 제공하는 service.
	 * @return
	 */
	List<Video> selectVideo();
	
	/**
	 * 내가 올린 글 조회
	 * @param videoUerId
	 * @return
	 */
	List<Video> selectMyVideo(String videoUserId);
	
	List<Video> viewVideoByArtist(String videoArtist);
	
	/**
	 * 좋아요가 많은 순서대로 video select.
	 * @return
	 */
	List<Video> selectTopLikeVideo();
}

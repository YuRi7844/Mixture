package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.Video;

public interface VideoDao {

	/**
	 * 동영상 등록
	 * @param video
	 * @return
	 */
	int insertVideo (Video video);
	
	/**
	 * 동영상 번호로 동영상 조회
	 * @param videoNo
	 * @return
	 */
	Video selectVideoByVideoNo (int videoNo);	
	
	/**
	 * 모든 동영상 조회
	 * @return
	 */
	List<Video> selectAllVideoByCategory (String category);
	
	
	/**
	 * 동영상의 정보를 수정
	 * @param video
	 * @return
	 */
	int updateVideo(Video video);
	
	/**
	 * 영상등록번호로 동영상정보를 제거.
	 * @param videoNo
	 * @return
	 */
	int deleteVideoByVideoNum(int videoNo);
	
	/**
	 * 영상 제목으로 영상 검색(카테고리 정보 자동 포함검색)
	 * @param videoCategory
	 * @param videoTitle
	 * @return
	 */
	List<Video> selectVideoByTitleAndCategory(String videoCategory, String videoTitle);
	
	/**
	 * 영상 아티스트로 영상 검색(카테고리 정보 자동 포함검색)
	 * @param videoCategory
	 * @param videoArtist
	 * @return
	 */
	List<Video> selectVideoByArtistAndCategory(String videoCategory, String videoArtist);
	
	/**
	 * 영상 게시자 아이디로 영상 검색(카테고리 정보 자동 포함검색)
	 * @param videoCategory
	 * @param videoUserId
	 * @return
	 */
	List<Video> selectVideoByUserIdAndCategory(String videoCategory, String videoUserId);
	
	/**
	 * 내용(추가글)으로 영상 검색(카테고리 정보 자도 포함)
	 * @param videoCategory
	 * @param videoContent
	 * @return
	 */
	List<Video> selectVideoByContentAndCategory(String videoCategory, String videoContent);
	
	/**
	 * 전체 Video 객체 select.
	 * @return
	 */
	List<Video> selectVideo();

	List<Video> selectVideoByArtistId(String videoArtist);
	
	/**
	 * 좋아요 count로 정렬한 video list select
	 * @return
	 */
	List<Video> selectVideoByLikeCount();
	
	/**
	 * 아티스트 유저의 영상글 게시물 수 체크.
	 * @param userId
	 * @return
	 */
	int selectUserVideoCount(String userId);
	
	/**
	 * 내가 올린 글 조회
	 * @return
	 */
	List<Video> selectMyVideo(String videoUserId);
}

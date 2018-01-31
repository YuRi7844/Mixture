package com.buskstop.dao;

import java.util.HashMap;
import java.util.List;

import com.buskstop.vo.VideoComment;

public interface VideoCommentDao {

	/**
	 * 동영상 댓글 등록
	 * @param videoComment
	 * @return
	 */
	int insertVideoComment(VideoComment videoComment);
	
	/**
	 * 동영상 번호로 해당 동영상의 댓글 모두 조회
	 * @param videoNo
	 * @return
	 */
	List<VideoComment> selectVideoCommentByVideoNo(int videoNo);
	
	/**
	 * 비디오 번호로 비디오 수정
	 * @param videoComment
	 * @return
	 */
	int updateVideoCommentByVideoCommentNo(VideoComment videoComment);

	int deleteVideoCommentByVideoCommentNo(int videoCommentNo);
	
	/**
	 * 동영상에 관련된 모든 comment delete dao.
	 * @param videoNo
	 * @return
	 */
	int deleteVideoCommentByVideoNo(int videoNo);
	
	/**
	 * 검색어를 담은 map 으로 videocomment list 를 select.
	 * @return
	 */
	List<VideoComment> selectVideoCommentByMap(HashMap<String, Object> map);
}

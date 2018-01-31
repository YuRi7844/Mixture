package com.buskstop.service;

import java.util.List;

import com.buskstop.vo.VideoComment;

public interface VideoCommentService {

	void insertVideoComment(VideoComment videoComment);
	
	List<VideoComment> selectVideoCommentByVideoNo(int videoNo);
	
	int updateVideoCommentByVideoCommentNo(VideoComment videoComment);
	
	int deleteVideoCommentByVideoCommentNo(int videoCommentNo);
	
	/**
	 * 관리자의 검색조건에 맞게 검색을 한다.
	 * @param videoNo
	 * @param userId
	 * @param content
	 * @return
	 */
	List<VideoComment> searchVideoCommentByAdmin(int videoNo,String userId, String content);
}

package com.buskstop.service;

import java.util.List;

import com.buskstop.vo.PerformanceComment;

public interface PerformanceCommentService {

	void insertPerformanceComment(PerformanceComment performanceComment);

	int deletePerformanceCommentByPerformanceCommentNo(int performanceCommentNo);

	void updatePerformanceComment(PerformanceComment performanceComment);
	
	List<PerformanceComment> listComment(int performanceNo);

	/**
	 * 검색조건에 맞는 PerformanceComment List 제공하는 Service.
	 * @param performanceNo
	 * @param userId
	 * @param content
	 * @return
	 */
	List<PerformanceComment> searchPerformanceComment(int performanceNo, String userId, String content);
	
	/**
	 * performancecomment 지우는 service.
	 * @param performanceCommentNo
	 * @return
	 */
	void deletePerformanceComment(int performanceCommentNo);
}

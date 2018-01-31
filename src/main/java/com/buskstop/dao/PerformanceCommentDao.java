package com.buskstop.dao;

import java.util.HashMap;
import java.util.List;

import com.buskstop.vo.PerformanceComment;

public interface PerformanceCommentDao {

	
	int insertPerformanceComment(PerformanceComment performanceComment);

	int updatePerformanceComment(PerformanceComment performanceComment);

	int deletePerformanceCommentByPerformanceCommentNo(int performanceCommentNo);

	List<PerformanceComment> listComment(int performanceNo);
	
	/**
	 * map (검색조건) 을 매개변수로 performanceComment list select
	 * @param map
	 * @return
	 */
	List<PerformanceComment> selectPerformanceComment(HashMap<String, Object> map);

}

package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.Performance;
import com.buskstop.vo.PerformanceLike;


public interface PerformanceLikeDao {
	/**
	 * 좋아요 추가
	 * @param like
	 * @return
	 */
	int insertPerformanceLike(PerformanceLike like);
	/**
	 * 좋아요 삭제 
	 * @param like
	 * @return
	 */
	int deletePerformanceLike(PerformanceLike like);
	
	List<PerformanceLike> selectperformanceLikeByPerformanceLikeNo(int num);
	
	int countPerformanceLikeByPerformanceLikeNo(int num);
	
	List<PerformanceLike> selectAllPerformanceLike();
	
	/**
	 * 유저의 id로 PerformanceList select하는 dao.
	 * @return
	 */
	List<Performance> selectPerformanceByLikeId(String id);
}

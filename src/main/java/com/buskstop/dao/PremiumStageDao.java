package com.buskstop.dao;

import java.util.HashMap;
import java.util.List;

import com.buskstop.vo.PremiumStage;

public interface PremiumStageDao {
	/**
	 * 공급자 insert
	 * @param supplier
	 * @return
	 */
	int insertPremiumStage(PremiumStage supplier);
	
	/**
	 * 공급자 정보 update
	 * @param supplier
	 * @return
	 */
	int updateStageSupplier(PremiumStage supplier);
	
	/**
	 * id로 공급자 정보 찾아오기.
	 * @param userId
	 * @return
	 */
	List<PremiumStage> selectSupplierById(String userId);

	
	/**
	 * userId로 프리미엄스테이지의 공연장제목 select.
	 * @param userId
	 * @return
	 */
	List<String> selectStageNameById(String userId);
	
	/**
	 * 사업장번호로 PremiumStage data select
	 * @param establishNum
	 * @return
	 */
	PremiumStage selectStageByEstablishNum(int establishNum);
	
	/**
	 * 사업장 번호로 PremiumStage data delete
	 * @param establishNum
	 * @return
	 */
	int deleteStageByEstablishNum(int establishNum);
	
	/*********************** 목록조회 & 검색 시 페이징 위한 Dao ***********************/
	/**
	 * 모든 프리미엄스테이지의 개수.
	 * @return
	 */
	int selectAllPremiumStageCount();
	
	/**
	 * 프리미엄 스테이지를 시작넘버부터 끝넘버까지 불러오는 dao
	 * @return
	 */
	List<PremiumStage> selectAllPremiumStage(int start, int end);

	
	/**
	 * 검색한 공연장 결과의 수를 select
	 * @param map
	 * @return
	 */
	int selectSearchStageCount(HashMap<String, Object> map);
	
	/**
	 * 검색 조건들을 비교해서 공연장 list return select dao.
	 * @param map
	 * @param start
	 * @param end
	 * @return
	 */
	List<PremiumStage> selectSearchStage(HashMap<String, Object> map ,int start, int end);
	
	/**
	 * 모든 PremiumStage Object select.
	 * @return
	 */
	List<PremiumStage> selectPremiumStage();
	
	/**
	 * 검색어를 담은 map으로 PremiumStage 객체 list select.
	 * @param map
	 * @return
	 */
	List<PremiumStage> selectPremiumStageBySearch(HashMap<String, String> map);
}

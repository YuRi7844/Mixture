package com.buskstop.dao;

import java.util.Date;
import java.util.List;

import com.buskstop.vo.PremiumStageTime;

public interface PremiumStageTimeDao {

	/**
	 * 옵션에 해당하는 시간코드들 등록
	 * @param premiumStageTime
	 * @return
	 */
	int insertPremiumStageTime(PremiumStageTime premiumStageTime);
	
	/**
	 * 옵션번호에 해당하는 시간코드들 조회
	 * @param optionNo
	 * @return
	 */
	List<Integer> selectPremiumStageTimeByStageRentalDate(Date date);
	
	/**
	 * 옵션번호로 시간코드 조회
	 * @param optionNo
	 * @return
	 */
	List<PremiumStageTime> selectPremiumStageTimeByOptionNo(int optionNo);
}

package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.PremiumStageOption;

public interface PremiumStageOptionDao {

	/**
	 * 예약옵션 등록
	 * @param premiumStageOption
	 * @return
	 */
	int insertPremiumStageOption(PremiumStageOption premiumStageOption);
	
	/**
	 * 예약옵션 번호로 해당하는 옵션삭제
	 * @param StageNo
	 * @return
	 */
	int deletePremiumStageOption(int optionNo);
	
	/**
	 * 사업장번호로 옵션조회
	 * @param establishNo
	 * @return
	 */
	List<PremiumStageOption> selectPremiumStageOptionByEstablishNo(int establishNo);
	
	/**
	 * 옵션상태 변경
	 * @param option
	 * @return
	 */
	int updatePremiumStageOptionStageState(PremiumStageOption option);
	
	/**
	 * 옵션정보와 그에 해당하는 시간테이블 한번에 조회
	 * @param establishNo
	 * @return
	 */
	List<PremiumStageOption> selectPremiumStageOptionByEstablishNoJoin(int establishNo);
	
	/**
	 * 옵션번호로 옵션정보와 시간테이블을 한번에 조회
	 * @param optionNo
	 * @return
	 */
	PremiumStageOption selectPremiumStageOptionByOptionNoJoin(int optionNo);
	
}

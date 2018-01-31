package com.buskstop.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buskstop.vo.Stage;

public interface StageDao {
	
	/**
	 * 공연장 등록
	 * @param stage
	 * @return
	 */
	int insertStage(Stage stage);

	Stage selectStageByStageNo(int stageNo);

	List<Stage> selectStage();

	List<Stage> selectAllStage(int beginNum, int endNum);

	int selectStageCount();

	int selectStageCountByStageDate(Date startDate, Date endDate);

	List<Stage> selectStageByStageDate(int beginItemInPage, int endItemInPage, Date startDate, Date endDate);
	int selectStageCountByLocation(String stageLocation, Date startDate, Date endDate);
	
	List<Stage> selectStageByStageLocation(int beginItemInPage, int endItemInPage, String stageLocation,
			Date startDate, Date endDate);


	/**
	 * 공연장 수정
	 * @param stage
	 * @return
	 */
	int updateStage(Stage stage);
	
	/**
	 * 공연장번호를 받아서 예약상태 변경
	 * @param stageNo
	 * @param stageReservation
	 * @return
	 */
	int updateStageForStageReservation(int stageReservation, int stageNo);


	int selectStageCountById(String stageSellerId, Date startDate, Date endDate);


	List<Stage> selectStageById(int beginItemInPage, int endItemInPage, String idSearch, Date startDate, Date endDate);

	List<Stage> selectStageOnlyId(int beginItemInPage, int endItemInPage, String idSearch);

	int selectStageCountOnlyId(String idSearch);

	int selectStageCountOnlyLocation(String locationSearch);

	List<Stage> selectStageOnlyLocation(int beginItemInPage, int endItemInPage, String locationSearch);


	List<Stage> selectStageByLocationAndName(int beginItemInPage, int endItemInPage, String nameSearch, Date startDate,
			Date endDate, String locationSearch);

	int selectStageCountByLocationAndName(String nameSearch, Date startDate, Date endDate, String locationSearch);

	List<Stage> selectStageOnlyName(int beginItemInPage, int endItemInPage, String nameSearch);

	int selectStageCountOnlyName(String stageName);

	List<Stage> selectStageByName(int beginItemInPage, int endItemInPage, String nameSearch, Date startDate,
			Date endDate);

	int selectStageCountByName(String name, Date startDate, Date endDate);

	int deleteStageByStageNo(int stageNo);

	List<Stage> selectStageByNameAndIdNoDate(int beginItemInPage, int endItemInPage, String nameSearch,
			String idSearch);

	int selectStageCountByNameAndIdNoDate(String nameSearch, String idSearch);

	List<Stage> selectStageByLocationAndIDNoDate(int beginItemInPage, int endItemInPage, String locationSearch,
			String idSearch);

	int selectStageCountByLocationAndIDNoDate(String locationSearch, String idSearch);

	List<Stage> selectStageByLocationAndNameNoDate(int beginItemInPage, int endItemInPage, String locationSearch,
			String nameSearch);

	int selectStageCountByLocationAndNameNoDate(String locationSearch, String nameSearch);

	/**
	 * 공연정보와 리뷰정보를 동시에 가져오는 dao.
	 * @return
	 */
	List<Stage> selectStageAndReview();
	
	/**
	 * 검색조건에 맞는 Stage object list 가져오는 dao.
	 * @return
	 */
	List<Stage> selectStageBySearch(HashMap<String, Object> map);
	
	/**
	 * 공급자아이디로 공연장 select
	 * @param stageSellerId
	 * @return
	 */
	List<Stage> selectStagebyStageSellerId(String stageSellerId);
	
	/**
	 * 공급자가 예약 거절할 경우 예약가능 상태 변경
	 * @param stageNo
	 * @return
	 */
	int rejectStageByStageNo(int stageNo);
	
	/**
	 * 내가 작성한 글
	 * @param sellerUserId
	 * @return
	 */
	List<Stage> selectMyStage(String stageSellerId);
}

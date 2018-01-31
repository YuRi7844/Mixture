package com.buskstop.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.buskstop.vo.PremiumStage;
import com.buskstop.vo.Stage;
import com.buskstop.vo.StageImage;
import com.buskstop.vo.StageReservation;

public interface StageService {
	/**
	 * 공연장 등록
	 * @param stage
	 */
	void insertStage(Stage stage);
	
	/**
	 * 공연장 사진 등록
	 * @param stageImage
	 */
	void insertStageImage(StageImage stageImage);
	
	/**
	 * 공연장예약 등록
	 * @param stageReservation
	 */
	void insertStageReservation(StageReservation stageReservation);

	Stage selectStageByStageNo(int stageNo);

	List<Stage> selectStage();

	Map<String, Object> selectAllStage(int page);
	
	/**
	 * 공연장 수정
	 * @param stage
	 */
	void updateStage(Stage stage);
	
	/**
	 * 공연장이미지 공연장 번호로 조회
	 * @param StageNo
	 * @return
	 */
	List<StageImage> selectStageImageByStageNo(int stageNo);
	
	/**
	 * 공연장이미지 공연장 번호로 삭제
	 * @param stageNo
	 */
	void deleteStageImageByStageNo(int stageNo);

	Map<String, Object> selectStageByStageDate(int page, Date startDate, Date endDate);


	/**
	 * 프리미엄공연장 사진등록.
	 * @param establishNum
	 * @param imageList
	 */
	void registStageImage(int establishNum, List<String> imageList);

	Map<String, Object> selectStageByStageLocation(int page, String stageLocation, Date startDate, Date endDate);

	/**
	 * 공연장 공연장 번호로 삭제
	 * @param stageNo
	 */
	void deleteStageByStageNo(int stageNo);
	
	/**
	 * 공연장사진 공연장사진 번호로 삭제
	 * @param stageImageNo
	 */
	void deleteStageImageByStageImageNo(int stageImageNo);

	Map<String, Object> selectStageByStageSellerId(int page, String idSearch, Date startDate, Date endDate);

	Map<String, Object> selectStageOnlyId(int page, String idSearch);

	Map<String, Object> selectStageOnlyLocation(int page, String locationSearch);
	
	Map<String, Object> selectStageOnlyName(int page, String nameSearch);

	Map<String, Object> selectStageByLocationAndName(int page, String locationSearch, Date startDate, Date endDate,
			String nameSearch);

	Map<String, Object> selectStageByName(int page, String nameSearch, Date startDate, Date endDate);
	
	/**
	 * 공연장 번호를 받아서 예약진행중이라면 조회
	 * @param stageNo
	 * @return
	 */
	StageReservation selectStageReservationByStageNoforRentalStateCode(int stageNo);
	
	/**
	 * 공연장 번호를 받아서 예약 상태 변경
	 * @param stageNo
	 * @param stageReservation
	 * @return
	 */
	Map<String, Object> updateStageForStageReservation(int stageReservation, int stageNo);
	
	Map<String, Object> selectStageByLocationAndNameNoDate(int page, String locationSearch, String nameSearch);

	Map<String, Object> selectStageByLocationAndIDNoDate(int page, String locationSearch, String idSearch);

	Map<String, Object> selectStageByNameAndIdNoDate(int page, String nameSearch, String idSearch);

	/**
	 * 공연장 번호를 받아서 진행중인 예약취소
	 * @param stageNo
	 */
	void cancelStageReservation(int stageNo);
	
	/* ######################################################### */
	
	/**
	 * 관리자가 볼 stage정보들을 제공하는 service.
	 * @return
	 */
	List<Stage> selectStageManagement();
	
	/**
	 * 검색조건에 맞는 stage 를 제공하는 service.
	 * @param reservation
	 * @param sDate
	 * @param eDate
	 * @param userId
	 * @return
	 */
	List<Stage> searchStageByAdmin(String reservation, Date sDate, Date eDate,String userId);
	/**
	 * 공급자 아이디를 받아서 공연장 조회
	 * @param stageSellerId
	 * @return
	 */
	List<Stage> selectStagebyStageSellerId(String stageSellerId);
	
	/**
	 * 공연장 아이디로 공연장예약 정보 조회
	 * @param stageNo
	 * @return
	 */
	List<StageReservation> selectStageReservationByStageNo(int stageNo);
	
	/**
	 * 공급자가 예약 승인
	 * @param stageNo
	 */
	void successStageReservation(int rentalNo);
	
	/**
	 * 공급자가 예약 취소
	 * @param stageNo
	 */
	void rejectStageReservation(int rentalNo);
	
	/**
	 * 공급자가 예약 취소할 경우 예약 가능 상태 변경
	 * @param stageNo
	 */
	void rejectStageByStageNo(int stageNo);
	
	/**
	 * 사용자가 신청한 예약 내역 조회
	 * @param rentalUserId
	 * @return
	 */
	List<StageReservation> selectMyStageApply(String rentalUserId);
	
	/**
	 * 대관번호로 예약정보 뽑아오기
	 * @param rentalNo
	 * @return
	 */
	StageReservation selectStageReservationByRentalNo(int rentalNo);
	
	void cancelStageReservationByRentalNo(int rentalNo);
	
	/**
	 * 내가 작성한 글
	 * @param stageSellerId
	 * @return
	 */
	List<Stage> selectMyStage(String stageSellerId);
}

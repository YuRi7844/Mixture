package com.buskstop.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.buskstop.vo.PremiumStage;
import com.buskstop.vo.Stage;
import com.buskstop.vo.StageImage;

public interface PremiumStageService {
	/**
	 * 공급자 등록
	 * 
	 * @param supplier
	 */
	void registerSupplier(PremiumStage supplier, List<String> imageList);

	/**
	 * PremiumStage 추가등록 service. (권한 등록 X)
	 * 
	 * @param stage
	 * @param imageList
	 */
	void addRegistStage(PremiumStage stage, List<String> imageList);

	/**
	 * 공급자 정보 수정
	 * 
	 * @param supplier
	 * @return
	 */
	int updateSupplier(PremiumStage supplier);

	/**
	 * Id로 공급자 정보 가져오기.
	 * 
	 * @param userId
	 * @return
	 */
	List<PremiumStage> selectSupplierById(String userId);

	/**
	 * 공연장 등록
	 * 
	 * @param stage
	 */
	void insertStage(Stage stage);

	/**
	 * 공연장 사진 등록
	 * 
	 * @param stageImage
	 */
	void insertStageImage(StageImage stageImage);

	Stage selectStageByStageNo(int stageNo);

	List<Stage> selectStage();

	Map<String, Object> selectAllStage(int page);

	/**
	 * 공연장 수정
	 * 
	 * @param stage
	 */
	void updateStage(Stage stage);

	/**
	 * 공연장이미지 공연장 번호로 조회
	 * 
	 * @param StageNo
	 * @return
	 */
	List<StageImage> selectStageImageByStageNo(int stageNo);

	/**
	 * 공연장이미지 공연장 번호로 삭제
	 * 
	 * @param stageNo
	 */
	void deleteStageImageByStageNo(int stageNo);

	Map<String, Object> selectStageByStageDate(int page, Date startDate, Date endDate);

	Map<String, Object> selectStageByInstrument(int page, String instrument, Date startDate, Date endDate);

	/**
	 * 프리미엄공연장 사진등록.
	 * 
	 * @param establishNum
	 * @param imageList
	 */
	void registStageImage(int establishNum, List<String> imageList);

	Map<String, Object> selectStageByStageLocation(int page, String stageLocation, Date startDate, Date endDate);

	/*
	 * ################################################# PremiumStage Service By 태경
	 * ★ ##################################################
	 */

	/**
	 * PremiumStageㅇ의 공연장 이름 리스트 가져오기.
	 * 
	 * @param userId
	 * @return
	 */
	List<String> viewMyStage(String userId);

	/**
	 * 사업장 번호로 공연장 정보 제공 service.
	 * 
	 * @param establishNum
	 * @return
	 */
	PremiumStage viewByEstablishNum(int establishNum);

	/**
	 * 프리미엄공급장 이미지 목록을 불러오기.
	 * 
	 * @param establishNum
	 * @return
	 */
	List<String> selectImageLocation(int establishNum);

	/**
	 * 공연장 정보를 update. 이미지 테이블 수정. & 프리미엄스테이지 정보 수정.
	 * 
	 * @param establishNum
	 * @param imageList
	 * @param stage
	 */
	PremiumStage updatePremiumStage(int establishNum, List<String> imageList, PremiumStage stage);

	/**
	 * 사업장번호로 프리미엄스테이지 삭제 service.
	 * 
	 * @param establishNum
	 */
	void deletePremiumStage(int establishNum, String userId);

	/**
	 * 모든 공연정보 페이징 처리 service.
	 * 
	 * @param page
	 * @return
	 */
	Map<String, Object> selectPremiumStage(int page);

	/**
	 * 	모든 조건 값들을 들고서 결과값을 return 해주는 service.
	 * @param nameSearch
	 * @param locationSearch
	 * @param startDate
	 * @param endDate
	 * @param idSearch
	 * @return
	 */
	Map<String, Object> searchPremiumStage(String nameSearch, String locationSearch, Date startDate, Date endDate,
			String idSearch, int page);

	
	
	/****************************************************************
	 * 							관리자용 service.
	 ****************************************************************/
	
	/**
	 * 모든 프리미엄 공연장 정보를 가져오는 service.
	 * @return
	 */
	List<PremiumStage> selectAllPremiumStage();
	
	/**
	 * 검색한 정보에 해당하는 PremiumStage 객체 list 를 제공하는 service.
	 * @param category
	 * @param search
	 * @return
	 */
	List<PremiumStage> selectPremiumStageByAdmin(String category, String search);
	
	/**
	 * 랜덤으로 4개의 프리미엄스테이지를 뽑아 제공하는 service.
	 * @return
	 */
	List<PremiumStage> mainPremiumStage();
}

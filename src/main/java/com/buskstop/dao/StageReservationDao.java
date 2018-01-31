package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.StageReservation;

public interface StageReservationDao {

	/**
	 * 공연장 예약 등록
	 * @param stageReservation
	 * @return
	 */
	int insertStageReservation(StageReservation stageReservation);
	
	/**
	 * 공연장 번호를 받아서 진행중인 예약이 있다면 조회
	 * @param stageNo
	 * @return
	 */
	StageReservation selectStageReservationByStageNoforRentalStateCode(int stageNo);
	
	/**
	 * 공연장 번호를 받아서 진행중인 예약 취소
	 * @param stageNo
	 * @return
	 */
	int cancelStageReservation(int stageNo);
	
	/**
	 * 공연장 번호로 공연장예약 정보 조회
	 * @param stageNo
	 * @return
	 */
	List<StageReservation> selectStageReservationByStageNo(int stageNo);

	/**
	 * 공급자가 예약 승인
	 * @param stageNo
	 * @return
	 */
	int successStageReservation(int rentalNo);
	
	/**
	 * 공급자가 예약 거절
	 * @param stageNo
	 * @return
	 */
	int rejectStageReservation(int rentalNo);
	
	/**
	 * 사용자 아이디로 신청한 예약내역 조회
	 * @param rentalUserId
	 * @return
	 */
	List<StageReservation> selectMyStageApply(String rentalUserId);
	
	/**
	 * 대관번호로 예약정보 조회
	 * @param rentalNo
	 * @return
	 */
	StageReservation selectStageReservationByRentalNo(int rentalNo);
	
	/**
	 * 사용자가 예약 신청 취소
	 * @param rentalNo
	 * @return
	 */
	int cancelStageReservationByRentalNo(int rentalNo);
}

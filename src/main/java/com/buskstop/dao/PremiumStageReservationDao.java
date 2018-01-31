package com.buskstop.dao;

import java.util.Date;
import java.util.List;

import com.buskstop.vo.PremiumStageReservation;

public interface PremiumStageReservationDao {

	/**
	 * 프리미엄 공연장 대관 신청
	 * @param reservation
	 * @return
	 */
	int insertPremiumStageReservation(PremiumStageReservation reservation);
	
	/**
	 * 프리미엄 공연장 신청 취소
	 * @param reservationNo
	 * @return
	 */
	int deletePremiumStageReservation(int reservationNo);
	
	/**
	 * 사업장번호로 사업장이 받은 대관신청 전체조회
	 * @param EstablishNo
	 * @return
	 */
	List<PremiumStageReservation> selectPremiumStageReservationByEstablishNo(int EstablishNo);
	
	/**
	 * 대관신청자 아이디로 사용자가 한 대관신청 전체조회
	 * @param userId
	 * @return
	 */
	List<PremiumStageReservation> selectPremiumStageReservationByUserId(String reservationUserId);
	
	/**
	 * 옵션번호로 예약 조회
	 * @param optionNo
	 * @return
	 */
	PremiumStageReservation selectPremiumStageReservationByOptionNo(int optionNo);
}

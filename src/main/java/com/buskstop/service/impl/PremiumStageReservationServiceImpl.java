package com.buskstop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.buskstop.dao.PremiumStageReservationDao;
import com.buskstop.dao.PremiumStageTimeDao;
import com.buskstop.dao.PremiumStageOptionDao;
import com.buskstop.service.PremiumStageReservationService;
import com.buskstop.vo.PremiumStageReservation;
import com.buskstop.vo.PremiumStageTime;
import com.buskstop.vo.PremiumStageOption;

@Service
public class PremiumStageReservationServiceImpl implements PremiumStageReservationService{
	
	@Autowired
	private PremiumStageReservationDao reservationDao;
	
	@Autowired
	private PremiumStageOptionDao optionDao;
	
	@Autowired
	private PremiumStageTimeDao timeDao;

	@Override
	public int createPremiumStageReservation(PremiumStageReservation reservation) {
		return reservationDao.insertPremiumStageReservation(reservation);
	}

	@Override
	public int removePremiumStageReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PremiumStageReservation> selectPremiumStageReservationByEstablishNo(int establishNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PremiumStageReservation> selectPremiumStageReservationByUserId(String userId) {
		return reservationDao.selectPremiumStageReservationByUserId(userId);
	}
	
	public PremiumStageReservation selectPremiumStageReservationByOptionNo(int optionNo) {
		return reservationDao.selectPremiumStageReservationByOptionNo(optionNo);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int createPremiumStageOption(PremiumStageOption option) {
		return optionDao.insertPremiumStageOption(option);
	}

	@Override
	public int removePremiumStageOption(int optionNo) {
		return optionDao.deletePremiumStageOption(optionNo);
	}

	@Override
	public List<PremiumStageOption> selectPremiumStageOptionByEstablishNo(int establishNo) {
		return optionDao.selectPremiumStageOptionByEstablishNo(establishNo);
	}

	@Override
	public int updatePremiumStageOptionStageState(PremiumStageOption option) {
		return optionDao.updatePremiumStageOptionStageState(option);
	}
	
	@Override
	public List<PremiumStageOption> selectPremiumStageOptionByEstablishNoJoin(int establishNo){
		return optionDao.selectPremiumStageOptionByEstablishNoJoin(establishNo);
	}
	
	@Override
	public PremiumStageOption selectPremiumStageOptionByOptionNoJoin(int optionNo) {
		return optionDao.selectPremiumStageOptionByOptionNoJoin(optionNo);
	}
	////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int createPremiumStageTime(PremiumStageOption option) {
		int result = 0;
		for(int i : option.getTimeList()) {
			result += timeDao.insertPremiumStageTime(new PremiumStageTime(option.getOptionNo(), i));
		}
		return result;
	}

	@Override
	public List<Integer> selectPremiumStageTimeByByStageRentalDate(Date date) {
		return timeDao.selectPremiumStageTimeByStageRentalDate(date);
	}

	@Override
	public List<PremiumStageTime> selectPremiumStageTimeByOptionNo(int optionNo) {
		return timeDao.selectPremiumStageTimeByOptionNo(optionNo);
	}
}

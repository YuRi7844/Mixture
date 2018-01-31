package com.buskstop.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.PremiumStageReservationDao;
import com.buskstop.vo.PremiumStageReservation;

@Repository
public class PremiumStageReservationDaoImpl implements PremiumStageReservationDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.premiumStageReservationMapper."+id;
	}

	@Override
	public int insertPremiumStageReservation(PremiumStageReservation reservation) {
		return session.insert(makeSqlId("insertPremiumStageReservation"), reservation);
	}

	@Override
	public int deletePremiumStageReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PremiumStageReservation> selectPremiumStageReservationByEstablishNo(int EstablishNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PremiumStageReservation> selectPremiumStageReservationByUserId(String reservationUserId) {
		return session.selectList(makeSqlId("selectPremiumStageReservationByUserId"), reservationUserId);
	}

	@Override
	public PremiumStageReservation selectPremiumStageReservationByOptionNo(int optionNo) {
		return session.selectOne(makeSqlId("selectPremiumStageReservationByOptionNo"), optionNo);
	}


}

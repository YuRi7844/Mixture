package com.buskstop.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.StageReservationDao;
import com.buskstop.vo.StageReservation;

@Repository
public class StageReservationDaoImpl implements StageReservationDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.stageReservationMapper."+id;
	}

	@Override
	public int insertStageReservation(StageReservation stageReservation) {
		return session.insert(makeSqlId("insertStageReservation"), stageReservation);
	}

	@Override
	public StageReservation selectStageReservationByStageNoforRentalStateCode(int stageNo) {
		return session.selectOne(makeSqlId("selectStageReservationByStageNoforRentalStateCode"), stageNo);
	}

	@Override
	public int cancelStageReservation(int stageNo) {
		return session.update(makeSqlId("cancelStageReservation"), stageNo);
	}
	
	@Override
	public List<StageReservation> selectStageReservationByStageNo(int stageNo){
		return session.selectList(makeSqlId("selectStageReservationByStageNo"), stageNo);
	}
	
	@Override
	public int successStageReservation(int rentalNo) {
		return session.update(makeSqlId("successStageReservation"), rentalNo);
	}
		
	@Override
	public int rejectStageReservation(int rentalNo) {
		return session.update(makeSqlId("rejectStageReservation"), rentalNo);
	}
	
	@Override
	public List<StageReservation> selectMyStageApply(String rentalUserId){
		return session.selectList(makeSqlId("selectMyStageApply"), rentalUserId);
	}
	
	@Override
	public StageReservation selectStageReservationByRentalNo(int rentalNo) {
		return session.selectOne(makeSqlId("selectStageReservationByRentalNo"), rentalNo);
	}

	@Override
	public int cancelStageReservationByRentalNo(int rentalNo) {
		return session.update(makeSqlId("cancelStageReservationByRentalNo"), rentalNo);
	}
}

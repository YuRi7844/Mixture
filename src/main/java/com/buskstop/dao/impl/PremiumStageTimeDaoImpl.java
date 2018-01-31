package com.buskstop.dao.impl;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.PremiumStageTimeDao;
import com.buskstop.vo.PremiumStageTime;

@Repository
public class PremiumStageTimeDaoImpl implements PremiumStageTimeDao{

	@Autowired
	SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.premiumStageTimeMapper."+id;
	}

	@Override
	public int insertPremiumStageTime(PremiumStageTime premiumStageTime) {
		return session.insert(makeSqlId("insertPremiumStageTime"), premiumStageTime);
	}

	@Override
	public List<Integer> selectPremiumStageTimeByStageRentalDate(Date date) {
		
		return session.selectList(makeSqlId("selectPremiumStageTimeByStageRentalDate"), date);
	}

	@Override
	public List<PremiumStageTime> selectPremiumStageTimeByOptionNo(int optionNo) {
		return session.selectList(makeSqlId("selectPremiumStageTimeByOptionNo"), optionNo);
	}
	
}

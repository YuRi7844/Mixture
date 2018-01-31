package com.buskstop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.PremiumStageDao;
import com.buskstop.vo.PremiumStage;

@Repository
public class PremiumStageDaoImpl implements PremiumStageDao{

	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public int insertPremiumStage(PremiumStage supplier) {
		return session.insert(makeSqlId("insertPremiumStage"), supplier);
	}
	
	@Override
	public int updateStageSupplier(PremiumStage supplier) {
		return session.update(makeSqlId("updateStageSupplier"), supplier);
	}
	
	@Override
	public List<PremiumStage> selectSupplierById(String userId) {
		return session.selectList(makeSqlId("selectSupplierById"), userId);
	}


	@Override
	public List<String> selectStageNameById(String userId) {
		return session.selectList(makeSqlId("selectStageNameById"), userId);
	}

	@Override
	public PremiumStage selectStageByEstablishNum(int establishNum) {
		return session.selectOne(makeSqlId("selectStageByEstablishNum"), establishNum);
	}

	@Override
	public int deleteStageByEstablishNum(int establishNum) {
		return session.delete(makeSqlId("deleteStageByEstablishNum"), establishNum);
	}
	
	/******************** 전체목록보기 ********************/
	
	@Override
	public int selectAllPremiumStageCount() {
		return session.selectOne(makeSqlId("selectAllPremiumStageCount"));
	}

	@Override
	public List<PremiumStage> selectAllPremiumStage(int start, int end) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("begin", start);
		map.put("end", end);
		return session.selectList(makeSqlId("selectAllPremiumStage"), map);
	}

	@Override
	public int selectSearchStageCount(HashMap<String, Object> map) {
		return session.selectOne(makeSqlId("selectSearchStageCount"), map);
	}

	@Override
	public List<PremiumStage> selectSearchStage(HashMap<String, Object> map, int start, int end) {
		map.put("begin", start);
		map.put("end", end);
		return session.selectList(makeSqlId("selectSearchStage"), map);
	}
	
	@Override
	public List<PremiumStage> selectPremiumStage() {
		return session.selectList(makeSqlId("selectPremiumStage"));
	}

	@Override
	public List<PremiumStage> selectPremiumStageBySearch(HashMap<String, String> map) {
		return session.selectList(makeSqlId("selectPremiumStageBySearch"), map);
	}

	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.premiumStageMapper."+id;
	}
}

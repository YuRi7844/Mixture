package com.buskstop.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.PerformanceDao;
import com.buskstop.vo.Performance;

@Repository
public class PerformanceDaoImpl implements PerformanceDao {

	@Autowired
	private SqlSessionTemplate session;

	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.performanceMapper." + id;
	}

	/********************************* insert Dao *********************************/

	@Override
	public int insertPerformance(Performance performance) {
		return session.insert(makeSqlId("insertPerformance"), performance);
	}

	/********************************* update Dao *********************************/

	/* 공연 정보 조회글 카운터 + 1, 공연글 정보 조회랑 세트로 호출 */
	@Override
	public int updatePerformanceCountByPerformanceNo(int performanceNo) {
		return session.update(makeSqlId("updatePerformanceCountByPerformanceNo"), performanceNo);
	}

	@Override
	public int updatePerformance(Performance performance) {
		System.out.println(performance);
		System.out.println(session.update(makeSqlId("updatePerformance"), performance));
		return session.update(makeSqlId("updatePerformance"), performance);
	}

	/********************************* delete Dao *********************************/

	@Override
	public int deletePerformanceByPerformanceNo(int performanceNo) {
		return session.delete(makeSqlId("deletePerformanceByPerformanceNo"), performanceNo);
	}

	/********************************* select Dao *********************************/

	@Override
	public Performance selectPerformanceByPerformanceNo(int performanceNo) {
		return session.selectOne(makeSqlId("selectPerformanceByPerformanceNo"), performanceNo);
	}

	// 전체 조회
	@Override
	public List<Performance> selectAllPerfor() {
		return session.selectList(makeSqlId("selectAllPerfor"));
	}

	/*****************************************************************************************/
	// 전체 조회 페이징
	@Override
	public List<Performance> selectPerformance(int beginItemNum, int endItemNum) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);

		return session.selectList(makeSqlId("selectPerformance"), map);
	}

	// 전체 조회 카운트
	@Override
	public int selectPerformanceCount() {
		return session.selectOne(makeSqlId("selectPerformanceCount"));
	}

	// 아티스트 공연정보 조회 페이징
	@Override
	public List<Performance> selectArtistPerformance(int beginItemNum, int endItemNum) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);

		return session.selectList(makeSqlId("selectArtistPerformance"), map);
	}

	// 아티스트 공연정보 카운트
	@Override
	public int selectArtistPerformanceCount() {
		return session.selectOne(makeSqlId("selectArtistPerformanceCount"));
	}

	/*****************************************************************************************/

	// 제목으로 검색 페이징
	@Override
	public List<Performance> selectPerformanceByPerformanceTitle(int beginItemNum, int endItemNum,
			String performanceTitle) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceTitle", performanceTitle);

		return session.selectList(makeSqlId("selectPerformanceByPerformanceTitle"), map);
	}

	// 제목으로 검색 count
	@Override
	public int selectPerformanceCountByTitle(String performanceTitle) {
		return session.selectOne(makeSqlId("selectPerformanceCountByTitle"), performanceTitle);
	}

	// 작성자로 검색 페이징
	@Override
	public List<Performance> selectPerformanceByPerformanceUserId(int beginItemNum, int endItemNum,
			String performanceUserId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceUserId", performanceUserId);

		return session.selectList(makeSqlId("selectPerformanceByPerformanceUserId"), map);
	}

	// 작성자로 검색 count
	@Override
	public int selectPerformanceCountByUserId(String userId) {
		return session.selectOne(makeSqlId("selectPerformanceCountByUserId"), userId);
	}

	// 공연장소로 검색 페이징
	@Override
	public List<Performance> selectPerformanceByPerformanceLocation(int beginItemNum, int endItemNum,
			String performanceLocation) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceLocation", performanceLocation);

		return session.selectList(makeSqlId("selectPerformanceByPerformanceLocation"), map);
	}

	// 공연장소로 검색 count
	@Override
	public int selectPerformanceCountByLocation(String location) {
		return session.selectOne(makeSqlId("selectPerformanceCountByLocation"), location);
	}

	// 공연이름으로 검색 페이징
	@Override
	public List<Performance> selectPerformanceByPerformanceName(int beginItemNum, int endItemNum,
			String performanceName) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceName", performanceName);

		return session.selectList(makeSqlId("selectPerformanceByPerformanceName"), map);
	}

	// 공연이름으로 검색 count
	@Override
	public int selectPerformanceCountByPerformanceName(String performanceName) {
		return session.selectOne(makeSqlId("selectPerformanceCountByPerformanceName"), performanceName);
	}

	// 공연내용으로 검색 페이징
	@Override
	public List<Performance> selectPerformanceByPerformanceContent(int beginItemNum, int endItemNum,
			String performanceContent) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceContent", performanceContent);

		return session.selectList(makeSqlId("selectPerformanceByPerformanceContent"), map);
	}

	// 공연이름으로 검색 count
	@Override
	public int selectPerformanceCountByPerformanceContent(String content) {
		return session.selectOne(makeSqlId("selectPerformanceCountByPerformanceContent"), content);
	}

	/*****************************************************************************************
	 * Artist Performance Search
	 *****************************************************************************************/

	// 제목으로 검색 페이징
	@Override
	public List<Performance> selectArtistPerformanceByPerformanceTitle(int beginItemNum, int endItemNum,
			String performanceTitle) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceTitle", performanceTitle);

		return session.selectList(makeSqlId("selectArtistPerformanceByPerformanceTitle"), map);
	}

	// 제목으로 검색 count
	@Override
	public int selectArtistPerformanceCountByTitle(String performanceTitle) {
		return session.selectOne(makeSqlId("selectArtistPerformanceCountByTitle"), performanceTitle);
	}

	// 작성자로 검색 페이징
	@Override
	public List<Performance> selectArtistPerformanceByPerformanceUserId(int beginItemNum, int endItemNum,
			String performanceUserId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceUserId", performanceUserId);

		return session.selectList(makeSqlId("selectArtistPerformanceByPerformanceUserId"), map);
	}

	// 작성자로 검색 count
	@Override
	public int selectArtistPerformanceCountByUserId(String userId) {
		return session.selectOne(makeSqlId("selectArtistPerformanceCountByUserId"), userId);
	}

	// 공연장소로 검색 페이징
	@Override
	public List<Performance> selectArtistPerformanceByPerformanceLocation(int beginItemNum, int endItemNum,
			String performanceLocation) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceLocation", performanceLocation);

		return session.selectList(makeSqlId("selectArtistPerformanceByPerformanceLocation"), map);
	}

	// 공연장소로 검색 count
	@Override
	public int selectArtistPerformanceCountByLocation(String location) {
		return session.selectOne(makeSqlId("selectArtistPerformanceCountByLocation"), location);
	}

	// 공연이름으로 검색 페이징
	@Override
	public List<Performance> selectArtistPerformanceByPerformanceName(int beginItemNum, int endItemNum,
			String performanceName) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceName", performanceName);

		return session.selectList(makeSqlId("selectArtistPerformanceByPerformanceName"), map);
	}

	// 공연이름으로 검색 count
	@Override
	public int selectArtistPerformanceCountByPerformanceName(String performanceName) {
		return session.selectOne(makeSqlId("selectArtistPerformanceCountByPerformanceName"), performanceName);
	}

	// 공연내용으로 검색 페이징
	@Override
	public List<Performance> selectArtistPerformanceByPerformanceContent(int beginItemNum, int endItemNum,
			String performanceContent) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("performanceContent", performanceContent);

		return session.selectList(makeSqlId("selectArtistPerformanceByPerformanceContent"), map);
	}

	// 공연이름으로 검색 count
	@Override
	public int selectArtistPerformanceCountByPerformanceContent(String content) {
		return session.selectOne(makeSqlId("selectArtistPerformanceCountByPerformanceContent"), content);
	}

	/***********
	 * Date
	 ***********/

	// 공연날짜로 검색 페이징
	@Override
	public List<Performance> selectPerformanceByPerformanceDate(int beginItemNum, int endItemNum, Date sDate,
			Date eDate) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("sDate", sDate);
		map.put("eDate", eDate);

		return session.selectList(makeSqlId("selectPerformanceByPerformanceDate"), map);
	}

	// 공연날짜로 검색 count
	@Override
	public int selectPerformanceCountByPerformanceDate(Date sDate, Date eDate) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);

		return session.selectOne(makeSqlId("selectPerformanceCountByPerformanceDate"), map);
	}

	// 아티스트공연 검색 paging
	@Override
	public List<Performance> selectArtistPerformanceByPerformanceDate(int beginItemNum, int endItemNum, Date sDate,
			Date eDate) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("sDate", sDate);
		map.put("eDate", eDate);

		return session.selectList(makeSqlId("selectArtistPerformanceByPerformanceDate"), map);
	}
	// 아티스트 공연 검색 카운트.
	@Override
	public int selectArtistPerformanceCountByPerformanceDate(Date sDate, Date eDate) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);

		return session.selectOne(makeSqlId("selectArtistPerformanceCountByPerformanceDate"), map);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// ID로 공연정보 조회.
	@Override
	public List<Performance> selectArtistPerformanceById(String id) {
		return session.selectList(makeSqlId("selectArtistPerformanceById"), id);
	}

	// PERFORMANCE LIST DAO.
	@Override
	public List<Performance> selectPerformance(int code) {
		return session.selectList(makeSqlId("adminSelectPerformance"), code);
	}

	// Admin Search Dao.
	@Override
	public List<Performance> selectPerformanceByAdminSearch(HashMap<String, Object> map) {
		return session.selectList(makeSqlId("selectPerformanceByAdminSearch"), map);
	}

	@Override
	public List<Performance> selectAllArtistPerformance() {
		return session.selectList(makeSqlId("selectAllArtistPerformance"));
	}
	
	@Override
	public List<Performance> selectMyPerformance(String performanceUserId){
		return session.selectList(makeSqlId("selectMyPerformance"), performanceUserId);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public List<Performance> selectPerformanceByLikeCount(){
		return session.selectList(makeSqlId("selectPerformanceByLikeCount"));
	}

	@Override
	public int selectArtistPerformanceCountById(String userId) {
		return session.selectOne(makeSqlId("selectArtistPerformanceCount"),userId);
	}
	
	
	
}

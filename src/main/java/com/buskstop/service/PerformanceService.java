package com.buskstop.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.buskstop.vo.Performance;

public interface PerformanceService {

	/**
	 * 공연정보 등록
	 * @param performance
	 */
	void insertPerformance(Performance performance);
	
	/* 공연정보 조회*/
	Performance getPerformanceByPerformanceNo(int performanceNo);
	/* 공연정보 조회 할 때 카운트 =+1 */
	void updatePerformanceCountByPerformanceNo(int performanceNo);

	/* 공연정보 삭제*/
	int deletePerformanceByPerformance(int performanceNo);

	/* 공연정보 수정*/
	void updatePerformance(Performance performance);
	
	/** 공연 정보 게시판 리스트 조회용
	 * @pram performance
	 * @return
	 */
	Performance PerformanceByPerformanceNo(int performanceNo);
	
	/**
	 * 일반공연정보 조회처리하는 메소드 페이징
	 * @param page
	 * @return
	 */
	Map<String, Object> selectPerformance(int page);
	/**
	 * 아티스트 공연정보 메소드 페이징
	 * @param page
	 * @return
	 */
	Map<String, Object> selectArtistPerformance(int page);
	
	/**
	 * 조회처리
	 * @return
	 */
	List<Performance> selectAllPerfor();
	
	/**
	 * 공연정보 제목으로 검색 페이징
	 * @param performanceTitle
	 * @return
	 */
	Map<String, Object> selectPerformanceByPerformanceTitle(int page, String performanceTitle);
	/**
	 * 공연정보 작성자로 검색 페이징
	 * @param performanceUserId
	 * @return
	 */
	Map<String, Object> selectPerformanceByPerformanceUserId(int page, String userId);
	/**
	 * 공연정보 공연장소로 검색 페이징
	 * @param performanceLocation
	 * @return
	 */
	Map<String, Object> selectPerformanceByPerformanceLocation(int page, String performanceLocation);
	/**
	 * 공연정보 공연이름으로 검색 페이징
	 * @param performanceName
	 * @return
	 */
	Map<String, Object> selectPerformanceByPerformanceName(int page, String performanceName);
	/**
	 * 공연정보 내용으로 검색 페이징
	 * @param performanceContent
	 * @return
	 */
	Map<String, Object> selectPerformanceByPerformanceContent(int page, String performanceContent);
	
	/**
	 * 아티스트공연정보 제목으로 검색페이징
	 * @param page
	 * @param performanceTitle
	 * @return
	 */
	Map<String, Object> selectArtistPerformanceByPerformanceTitle(int page,String performanceTitle);

	/**
	 * 아티스트공연정보 유저id로 검색페이징
	 * @param page
	 * @param userId
	 * @return
	 */
	Map<String, Object> selectArtistPerformanceByPerformanceUserId(int page, String userId);

	/**
	 * 아티스트 공연정보 공연장소로 검색페이징
	 * @param page
	 * @param performanceLocation
	 * @return
	 */
	Map<String, Object> selectArtistPerformanceByPerformanceLocation(int page, String performanceLocation);

	/**
	 * 아티스트 공연정보 공연이름으로 검색페이징
	 * @param page
	 * @param performanceName
	 * @return
	 */
	Map<String, Object> selectArtistPerformanceByPerformanceName(int page, String performanceName);
	
	/**
	 * 아티스트 공연정보 공연내용으로 검색페이징
	 * @param page
	 * @param performanceContent
	 * @return
	 */
	Map<String, Object> selectArtistPerformanceByPerformanceContent(int page, String performanceContent);
	
	/**
	 * 공연정보 날짜로 검색 페이징
	 * @param page
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	Map<String, Object> selectPerformanceByPerformanceDate(int page, Date sDate, Date eDate);
	
	/**
	 * 아티스트공연 날짜로 검색 페이징.
	 * @param page
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	Map<String, Object> selectArtistPerformanceByPerformanceDate(int page, Date sDate, Date eDate);
	
	/**
	 * id 를 매개변수로 등록한 공연정보 조회.
	 * @param id
	 * @return
	 */
	List<Performance> selectPerformanceById(String id);
	
	/**
	 * 일반 공연정보 전체목록 조회.
	 * @return
	 */
	List<Performance> selectNormalPerformance();
	
	/**
	 * 검색조건을 파라미터로 Performance list 제공하는 service
	 * @param category
	 * @param search
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Performance> selectPerformanceBySearch(String category,String search,Date startDate,Date endDate,int code);
	
	/**
	 * 
	 * @return
	 */
	List<Performance> selectArtistPerformance();
	
	/**
	 * 내 작성글 보기
	 * @param performanceUserId
	 * @return
	 */
	List<Performance> selectMyPerformance(String performanceUserId);
	/**
	 * 좋아요수가 많은 Performance 객체 정리.
	 * @return
	 */
	List<Performance> selectTopLikePerformance();
	
}

package com.buskstop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buskstop.common.util.PagingBean;
import com.buskstop.dao.StageDao;
import com.buskstop.dao.StageImageDao;
import com.buskstop.dao.StageReservationDao;
import com.buskstop.dao.StageReviewDao;
import com.buskstop.service.StageService;
import com.buskstop.vo.Stage;
import com.buskstop.vo.StageImage;
import com.buskstop.vo.StageReservation;
import com.buskstop.vo.StageReview;

@Service
public class StageServiceImpl implements StageService{
	
	
	@Autowired
	private StageDao stageDao;
	
	@Autowired
	private StageImageDao stageImageDao;
	
	@Autowired
	private StageReservationDao stageReservationDao;
	
	@Autowired
	private StageReviewDao stageReviewDao;
	
	@Override
	public void insertStage(Stage stage) {
		stageDao.insertStage(stage);
	}
	
	@Override
	public void insertStageImage(StageImage stageImage) {
		stageImageDao.insertStageImage(stageImage);
	}
	
	@Override
	public void insertStageReservation(StageReservation stageReservation) {
		stageReservationDao.insertStageReservation(stageReservation);
	}
	
	@Override
	public Stage selectStageByStageNo(int stageNo) {
		return stageDao.selectStageByStageNo(stageNo);
	}
	
	@Override
	public List<Stage> selectStage() {
		return stageDao.selectStage();
	}
	
	@Override
	public Map<String, Object> selectAllStage(int page){
		System.out.println("서비스");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb = new PagingBean(stageDao.selectStageCount(),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectAllStage(pb.getBeginItemInPage(),pb.getEndItemInPage());
		map.put("list",list);		
		return map;
	}
	
	@Override
	public Map<String,Object> selectStageByStageLocation(int page, String locationSearch, Date startDate, Date endDate){
		System.out.println("장소 검색");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb= new PagingBean(stageDao.selectStageCountByLocation(locationSearch,startDate,endDate),page);
		System.out.println("페이징 : "+pb);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByStageLocation(pb.getBeginItemInPage(), pb.getEndItemInPage(), locationSearch,startDate,endDate);
		map.put("list",list);
		System.out.println("장소 검색하고 뽑아온 리스트 : "+list);
		return map;
	}

	@Override
	public Map<String, Object> selectStageByName(int page, String nameSearch, Date startDate, Date endDate) {
		System.out.println("악기 검색");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb= new PagingBean(stageDao.selectStageCountByName(nameSearch,startDate,endDate),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByName(pb.getBeginItemInPage(), pb.getEndItemInPage(), nameSearch,startDate,endDate);
		map.put("list",list);
		System.out.println("악기 검색하고 뽑아온 리스트 : "+list);
		return map;
	}

	@Override
	public Map<String, Object> selectStageByStageDate(int page, Date startDate, Date endDate) {
		System.out.println("날짜 검색");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb= new PagingBean(stageDao.selectStageCountByStageDate(startDate,endDate),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByStageDate(pb.getBeginItemInPage(), pb.getEndItemInPage(), startDate, endDate);
		map.put("list", list);
		return map;
	}
	
	@Override
	public Map<String, Object> selectStageByStageSellerId(int page, String idSearch,Date startDate, Date endDate) {
		System.out.println("아이디 검색");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb= new PagingBean(stageDao.selectStageCountById(idSearch,startDate,endDate),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageById(pb.getBeginItemInPage(), pb.getEndItemInPage(), idSearch,startDate,endDate);
		map.put("list",list);
		System.out.println("악기 검색하고 뽑아온 리스트 : "+list);
		return map;
	}
	
	@Override
	public Map<String,Object> selectStageOnlyId(int page, String idSearch){
		System.out.println("단일 아이디 검색");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb = new PagingBean(stageDao.selectStageCountOnlyId(idSearch),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageOnlyId(pb.getBeginItemInPage(),pb.getEndItemInPage(),idSearch);
		map.put("list",list);
		return map;
	}
	
	@Override
	public Map<String,Object> selectStageOnlyLocation(int page, String locationSearch){
		System.out.println("단일 장소 검색");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb = new PagingBean(stageDao.selectStageCountOnlyLocation(locationSearch),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageOnlyLocation(pb.getBeginItemInPage(),pb.getEndItemInPage(),locationSearch);
		map.put("list",list);
		return map;
	}
	
	@Override
	public Map<String,Object> selectStageOnlyName(int page, String nameSearch){
		System.out.println("단일 악기 검색");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb = new PagingBean(stageDao.selectStageCountOnlyName(nameSearch),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageOnlyName(pb.getBeginItemInPage(),pb.getEndItemInPage(),nameSearch);
		map.put("list",list);
		System.out.println("단일 악기 서비스까지 나오는 리스트"+list);
		return map;
	}
	
	@Override
	public Map<String,Object> selectStageByLocationAndName(int page, String locationSearch, Date startDate, Date endDate, String nameSearch){
		System.out.println("장소 검색");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb= new PagingBean(stageDao.selectStageCountByLocationAndName(locationSearch,startDate,endDate,nameSearch),page);
		System.out.println("페이징 : "+pb);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByLocationAndName(pb.getBeginItemInPage(), pb.getEndItemInPage(), locationSearch,startDate,endDate,nameSearch);
		map.put("list",list);
		System.out.println("장소 검색하고 뽑아온 리스트 : "+list);
		return map;
	}
	
	
	@Override
	public void updateStage(Stage stage) {
		stageDao.updateStage(stage);
	}
	
	@Override
	public List<StageImage> selectStageImageByStageNo(int stageNo) {
		return stageImageDao.selectStageImageByStageNo(stageNo);
	}
	
	@Override
	public void deleteStageImageByStageNo(int stageNo) {
		stageImageDao.deleteStageImageByStageNo(stageNo);
	}

	@Override
	@Transactional
	public void registStageImage(int establishNum, List<String> imageList) {
		for(String stageImage : imageList) {
			stageImageDao.insertStageImage(new StageImage(1, stageImage, establishNum));
		}
	}
	
	@Override
	public void deleteStageImageByStageImageNo(int stageImageNo) {
		stageImageDao.deleteStageImageByStageImageNo(stageImageNo);
	}

	@Override
	public void deleteStageByStageNo(int stageNo) {
		stageDao.deleteStageByStageNo(stageNo);
		
	}

	@Override
	public Map<String, Object> selectStageByLocationAndNameNoDate(int page, String locationSearch, String nameSearch) {
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb= new PagingBean(stageDao.selectStageCountByLocationAndNameNoDate(locationSearch,nameSearch),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByLocationAndNameNoDate(pb.getBeginItemInPage(), pb.getEndItemInPage(), locationSearch,nameSearch);
		map.put("list",list);
		return map;
	}

	@Override
	public Map<String, Object> selectStageByLocationAndIDNoDate(int page, String locationSearch, String idSearch) {
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb= new PagingBean(stageDao.selectStageCountByLocationAndIDNoDate(locationSearch,idSearch),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByLocationAndIDNoDate(pb.getBeginItemInPage(), pb.getEndItemInPage(), locationSearch,idSearch);
		map.put("list",list);
		return map;
	}

	@Override
	public Map<String, Object> selectStageByNameAndIdNoDate(int page, String nameSearch, String idSearch) {
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb= new PagingBean(stageDao.selectStageCountByNameAndIdNoDate(nameSearch,idSearch),page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByNameAndIdNoDate(pb.getBeginItemInPage(), pb.getEndItemInPage(), nameSearch,idSearch);
		map.put("list",list);
		return map;
	}
	
	@Override
	public StageReservation selectStageReservationByStageNoforRentalStateCode(int stageNo) {
		return stageReservationDao.selectStageReservationByStageNoforRentalStateCode(stageNo);
	}
	
	@Override
	public Map<String, Object> updateStageForStageReservation(int stageReservation, int stageNo){
		HashMap<String, Object> map = new HashMap<>();
		map.put("stageReservation", stageReservation);
		map.put("stageNo", stageNo);
		
		stageDao.updateStageForStageReservation(stageReservation, stageNo);
		
		return map;
	}
	
	@Override
	public void cancelStageReservation(int stageNo) {
		stageReservationDao.cancelStageReservation(stageNo);
	}
	
	
	
	/************************ 관리자페이지용도 - 건들지마시오 (태경) ************************/
	
	public List<Stage> selectStageManagement() {
		List<Stage> list = stageDao.selectStageAndReview();
		
		for(Stage s : list) {
			List<StageReview> reviewList = s.getReviewList();
			int sum = 0;
			for(StageReview r : reviewList) {
				sum += r.getStarScore();
			}
			s.setStarpointAvg(sum);
		}
		return list;
	}

	@Override
	public List<Stage> searchStageByAdmin(String reservation, Date sDate, Date eDate, String userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("reservation", reservation);
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		map.put("userId", userId);
		
		System.out.println(map);
		
		return stageDao.selectStageBySearch(map);
	}
	
	@Override
	public List<Stage> selectStagebyStageSellerId(String stageSellerId){
		return stageDao.selectStagebyStageSellerId(stageSellerId);
	}
	
	@Override
	public List<StageReservation> selectStageReservationByStageNo(int stageNo){
		return stageReservationDao.selectStageReservationByStageNo(stageNo);
	}
	
	@Override
	public void successStageReservation(int rentalNo) {
		stageReservationDao.successStageReservation(rentalNo);
	}
	
	@Override
	public void rejectStageReservation(int rentalNo) {
		stageReservationDao.rejectStageReservation(rentalNo);
	}
	
	@Override
	public void rejectStageByStageNo(int stageNo) {
		stageDao.rejectStageByStageNo(stageNo);
	}
	
	@Override
	public List<StageReservation> selectMyStageApply(String rentalUserId){
		return stageReservationDao.selectMyStageApply(rentalUserId);
	}
	
	@Override
	public StageReservation selectStageReservationByRentalNo(int rentalNo) {
		return stageReservationDao.selectStageReservationByRentalNo(rentalNo);
	}
	
	@Override
	public void cancelStageReservationByRentalNo(int rentalNo) {
		stageReservationDao.cancelStageReservationByRentalNo(rentalNo);
	}
	
	@Override
	public List<Stage> selectMyStage(String stageSellerId){
		return stageDao.selectMyStage(stageSellerId);
	}
}

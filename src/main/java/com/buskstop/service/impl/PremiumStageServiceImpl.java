package com.buskstop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buskstop.common.util.PagingBean;
import com.buskstop.dao.AuthorityDao;
import com.buskstop.dao.PremiumStageDao;
import com.buskstop.dao.PremiumStageImageDao;
import com.buskstop.dao.StageDao;
import com.buskstop.dao.StageImageDao;
import com.buskstop.service.PremiumStageService;
import com.buskstop.vo.Authority;
import com.buskstop.vo.PremiumStage;
import com.buskstop.vo.PremiumStageImage;
import com.buskstop.vo.Stage;
import com.buskstop.vo.StageImage;

@Service
public class PremiumStageServiceImpl implements PremiumStageService {

	@Autowired
	private AuthorityDao authorDao;

	@Autowired
	private PremiumStageDao supplierDao;

	@Autowired
	private PremiumStageImageDao premiumImageDao;

	@Autowired
	private StageDao stageDao;

	@Autowired
	private StageImageDao stageImageDao;

	@Override
	public int updateSupplier(PremiumStage supplier) {
		return supplierDao.updateStageSupplier(supplier);
	}

	@Override
	public List<PremiumStage> selectSupplierById(String userId) {
		return supplierDao.selectSupplierById(userId);
	}

	@Override
	public void insertStage(Stage stage) {
		stageDao.insertStage(stage);
	}

	@Override
	public void insertStageImage(StageImage stageImage) {
		stageImageDao.insertStageImage(stageImage);
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
	public Map<String, Object> selectAllStage(int page) {
		System.out.println("서비스");
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb = new PagingBean(stageDao.selectStageCount(), page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectAllStage(pb.getBeginItemInPage(), pb.getEndItemInPage());
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> selectStageByStageLocation(int page, String stageLocation, Date startDate,
			Date endDate) {
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb = new PagingBean(stageDao.selectStageCountByLocation(stageLocation, startDate, endDate), page);
		System.out.println("위치:" + stageLocation);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByStageLocation(pb.getBeginItemInPage(), pb.getEndItemInPage(),
				stageLocation, startDate, endDate);
		map.put("list", list);
		return map;
	}
	
	@Override
	public Map<String, Object> selectStageByInstrument(int page, String instrument, Date startDate, Date endDate) {
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb = new PagingBean(stageDao.selectStageCountByName(instrument, startDate, endDate), page);

		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByName(pb.getBeginItemInPage(), pb.getEndItemInPage(), instrument,
				startDate, endDate);
		map.put("list", list);

		return map;
	}

	@Override
	public Map<String, Object> selectStageByStageDate(int page, Date startDate, Date endDate) {
		HashMap<String, Object> map = new HashMap<>();
		PagingBean pb = new PagingBean(stageDao.selectStageCountByStageDate(startDate, endDate), page);
		map.put("pageBean", pb);
		List<Stage> list = stageDao.selectStageByStageDate(pb.getBeginItemInPage(), pb.getEndItemInPage(), startDate,
				endDate);
		map.put("list", list);
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

	/********************************* 공급자 등록 *********************************/
	@Override
	@Transactional
	public void registerSupplier(PremiumStage supplier, List<String> imageList) {
		// 공급자 정보 넣기
		supplierDao.insertPremiumStage(supplier);
		// 권한 넣기
		authorDao.insertAuthority(new Authority(supplier.getOperatorUserId(), "ROLE_PRODUCER"));
		// 이미지 db에 넣기
		for (String s : imageList) {
			premiumImageDao.insertImage(new PremiumStageImage(1, s, supplier.getEstablishNum()));
		}
	}

	@Override
	public void addRegistStage(PremiumStage stage, List<String> imageList) {
		supplierDao.insertPremiumStage(stage);
		for (String s : imageList) {
			premiumImageDao.insertImage(new PremiumStageImage(1, s, stage.getEstablishNum()));
		}
	}

	// 왜 추가했나요? 답변 해주시면 내공 100
	@Override
	public void registStageImage(int establishNum, List<String> imageList) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> viewMyStage(String userId) {
		return supplierDao.selectStageNameById(userId);
	}

	@Override
	public PremiumStage viewByEstablishNum(int establishNum) {
		return supplierDao.selectStageByEstablishNum(establishNum);
	}

	@Override
	public List<String> selectImageLocation(int establishNum) {
		return premiumImageDao.selectImageByEstablishNum(establishNum);
	}

	@Override
	@Transactional
	public PremiumStage updatePremiumStage(int establishNum, List<String> imageList, PremiumStage stage) {
		// image 삭제 (establishNum을 받아서 지운다)
		premiumImageDao.deleteImageByEstablishNum(establishNum);

		// stage update (supplierDao로 update)
		System.out.println(stage+" : stageService");
		supplierDao.updateStageSupplier(stage);

		// image 등록 (imageList의 이미지들을 dao로 저장)
		for (String image : imageList) {
			premiumImageDao.insertImage(new PremiumStageImage(1, image, stage.getEstablishNum()));
		}

		return stage;
	}

	@Override
	@Transactional
	public void deletePremiumStage(int establishNum, String userId) {
		
		// test
		System.out.println(establishNum);
		System.out.println(userId);
		
		// image를 삭제하고
		premiumImageDao.deleteImageByEstablishNum(establishNum);
		
		// user의 공연장 소유 개수 확인.
		List<PremiumStage> stageList =  supplierDao.selectSupplierById(userId);
		if(stageList.size()==1) {
			// 현재 삭제하려는 공연장이 마지막 공연장이면? 권한 삭제. 그리고 index로 보낸다 
			supplierDao.deleteStageByEstablishNum(establishNum);
			authorDao.deleteAuthorityById(userId);
		} else {
			// 아니라면 establishNum 으로 공연장만 삭제한다.
			supplierDao.deleteStageByEstablishNum(establishNum);
		}
		
		
	}
	
	// 모든 프리미엄 공연장 목록 보여주는 페이징.
	@Override
	public Map<String,Object> selectPremiumStage(int page){
		HashMap<String, Object> map = new HashMap<>();
		
		// PagingBean 생성
		PagingBean pb = new PagingBean(supplierDao.selectAllPremiumStageCount(),page);
		
		// Performance list 불러오기
		List<PremiumStage> list = supplierDao.selectAllPremiumStage(pb.getBeginItemInPage(), pb.getEndItemInPage());
		
		map.put("pageBean", pb);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> searchPremiumStage(String nameSearch, String locationSearch, Date startDate,
			Date endDate, String idSearch,int page) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("nameSearch", nameSearch);
		map.put("locationSearch", locationSearch);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		if(!(idSearch.equals("")) && !(idSearch==null) ) {
			map.put("idSearch",idSearch);
		}
		
		// Paging Bean 객체생성.
		PagingBean pb = new PagingBean(supplierDao.selectSearchStageCount(map) , page);
		
		// Performance list 불러오기.
		List<PremiumStage> list = supplierDao.selectSearchStage(map,pb.getBeginItemInPage(),pb.getEndItemInPage());
		
		System.out.println("---------------------------------------");
		HashMap<String, Object> returnMap = new HashMap<>();
		returnMap.put("pageBean", pb);
		returnMap.put("list", list);
		
		return returnMap;
	}

	/*********************************************************************/
	
	@Override
	public List<PremiumStage> selectAllPremiumStage() {
		List<PremiumStage> list = supplierDao.selectPremiumStage();
		return list;
	}

	@Override
	public List<PremiumStage> selectPremiumStageByAdmin(String category, String search) {
		HashMap<String, String> map = new HashMap<>();
		
		if(category.equals("establishNo")) {
			map.put("establishNum", search);
		} else if(category.equals("userId")) {
			map.put("operatorUserId", search);
		} else if(category.equals("location")) {
			map.put("stageLocation", search);
		}
		
		return supplierDao.selectPremiumStageBySearch(map);
	}
	
	
	public Map<String,Object> selectPremiumDetailView(int establishNum){
		HashMap<String, Object> map = new HashMap<>();
		PremiumStage stage = supplierDao.selectStageByEstablishNum(establishNum);
		List<String> list = premiumImageDao.selectImageByEstablishNum(establishNum);
		
		map.put("premiumStage", stage);
		map.put("imageList", list);
		
		return map;
	}

	@Override
	public List<PremiumStage> mainPremiumStage(){
		List<PremiumStage> list = supplierDao.selectPremiumStage();
		List<PremiumStage> newList = new ArrayList<>();
		System.out.println(list);
		if(list.size()==0) {
			return null;
		}else {
			if(list.size()<5) {
				newList.addAll(list);
			}else {
				for(Integer i : randomFourNumber(list.size())) {
					newList.add(list.get(i));
				}
			}
		}
		
		return newList;
	}
	
	public List<Integer> randomFourNumber(int size){

		List<Integer> list = new ArrayList<>();
		if(list.size()==0) {
			return null;
		}
		for(int i=0;i<4;i++) {
			list.add((int)Math.random()*(size-1));
			for(int j=size;j>0;j--) {
				if(list.get(i)==list.get(j)) {
					i--;
				}
			}
		}
		
		return list;
	}
	

}

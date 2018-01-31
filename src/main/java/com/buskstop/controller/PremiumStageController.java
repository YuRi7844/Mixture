package com.buskstop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.PremiumStageReservationService;
import com.buskstop.service.PremiumStageService;
import com.buskstop.service.UserService;
import com.buskstop.vo.PremiumStage;
import com.buskstop.vo.PremiumStageOption;
import com.buskstop.vo.User;

@Controller
public class PremiumStageController {

	@Autowired
	MyPageController mpc;
	
	@Autowired
	private PremiumStageService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PremiumStageReservationService reservationService;

	// service.selectSupplierById 는 id로 프리미엄공연장 리스트를 부르는 service인데 이름수정하기 귀찮아서 그냥함.★

	@RequestMapping("/producer/menu")
	public ModelAndView goPremiumStageEdit() {
		ModelAndView mav = mpc.profileInfo();
		// 프리미엄 대관공급자 선택메뉴로 이동하는 controller
		mav.setViewName("premiumStage/premiumStageEditCategory.myTemp");
		
		return mav;
	}

	/**********************
	 * premiumStageEditCategory view에서 이동
	 **********************/
	// 조회
	@RequestMapping("/producer/selectViewPremiumStage")
	public ModelAndView selectPremiumStageView(String userId) {
		ModelAndView mav = mpc.profileInfo();
		// userId로 사용자가 등록한 premiumstage list 객체를 전달.
		mav.addObject("list", service.selectSupplierById(userId));
		mav.setViewName("premiumStage/choiceViewStage.myTemp");
		return mav;
	}

	// 수정
	@RequestMapping("/producer/selectEditPremiumStage")
	public ModelAndView selectPremiumStageEdit(String userId) {
		ModelAndView mav = mpc.profileInfo();
		
		mav.addObject("list", service.selectSupplierById(userId));
		mav.setViewName("/producer/goStageUpdateView.do");
		return mav;
	}

	/********************** premiumStage 공급자용 상세보기 페이지 **********************/
	@RequestMapping("/producer/myStageDetail")
	public ModelAndView viewMyStage(int establishNum) {
		
		ModelAndView mav = mpc.profileInfo();
		
		// 이미지 목록 가져오고 스테이지 정보가져와서 보내기.
		List<String> imageList = service.selectImageLocation(establishNum);
		PremiumStage stage = service.viewByEstablishNum(establishNum);

		Map<String, Object> map = new HashMap<>();
		map.put("imageList", imageList);
		map.put("premiumStage", stage);
		
		List<PremiumStageOption> optionList = reservationService.selectPremiumStageOptionByEstablishNoJoin(establishNum);
		map.put("optionList", optionList);
		
		mav.addObject("map", map);
		mav.setViewName("premiumStage/myStageDetailView.myTemp");
		
		return mav;
	}
	
	/**
	 * 일반 회원용 프리미엄 공급장 상세보기페이지
	 * @param establishNo
	 * @return
	 */
	@RequestMapping("/goPremiumStageDetailView")
	public ModelAndView goPremiumStageDetailView(int establishNo) {
		// 이미지 목록 가져오고 스테이지 정보가져와서 보내기.
		List<String> imageList = service.selectImageLocation(establishNo);
		PremiumStage stage = service.viewByEstablishNum(establishNo);

		Map<String, Object> map = new HashMap<>();
		map.put("imageList", imageList);
		map.put("premiumStage", stage);
		
		List<PremiumStageOption> optionList = reservationService.selectPremiumStageOptionByEstablishNoJoin(establishNo);
		map.put("optionList", optionList);
		
		return new ModelAndView("premiumStage/premiumStageDetailView.tiles", "map", map);
	}
	
	/********************** premiumStage 정보수정 페이지로 이동 **********************/
   @RequestMapping("/producer/goStageUpdateView")
   public ModelAndView goUpdateView(int establishNum) {
      List<String> imageList = service.selectImageLocation(establishNum);
      PremiumStage stage = service.viewByEstablishNum(establishNum);
      Map<String, Object> map = new HashMap<>();
      map.put("imageList", imageList);
      map.put("premiumStage", stage);
      return new ModelAndView("premiumStage/updateStageView.tiles", "map", map);
   }

	/************************
	 * premiumStage 정보수정 controller
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 **********************/

	@RequestMapping("/producer/premiumStageUpdate")
	public ModelAndView premiumStageUpdate(@ModelAttribute PremiumStage stage, HttpServletRequest request)
			throws IllegalStateException, IOException {
		System.out.println(stage+"premiumstageController");
		/*
		 * updatecontroller 흐름. 1. 기존에 있던 image 테이블의 데이터삭제. 2. 새로 받은 multiImage들 list에
		 * 담아서 넣기. 3. supplier의 정보 update. 4. map에 이미지 리스트, supplier 담아서 다음 페이지에 넘기기.
		 */
		// 파라미터로 넘어온 image들
		List<MultipartFile> list = stage.getMultiImage();
		// 테이블에 넣을 image들
		List<String> imageList = new ArrayList<>();
		if (!(stage.getOldImage() == null) && !(stage.getOldImage().size() == 0)) {
			for (String s : stage.getOldImage()) {
				imageList.add(s);
			}
		}
		if (!(list == null) && !(list.size() == 0)) {
			for (MultipartFile image : list) {
				int i = 0;
				String dir = request.getServletContext().getRealPath("/supplierImage");
				String fileName = UUID.randomUUID().toString();
				File upImage = new File(dir, fileName + ".jpg");
				image.transferTo(upImage);
				imageList.add(fileName + ".jpg");
			}
		}
		
		if(imageList.size()==0) {
			goUpdateView(stage.getEstablishNum());
		}
		
		stage.setStageImage(imageList.get(0));

		// service에서 처리해 줄것 : image 기존거 삭제 & 추가 / supplier 의 정보 update
		stage = service.updatePremiumStage(stage.getEstablishNum(), imageList, stage);

		// map에 넣어서 보낸다.
		Map<String, Object> map = new HashMap<>();
		map.put("imageList", imageList);
		map.put("premiumStage", stage);

		return new ModelAndView("premiumStage/myStageDetailView.tiles", "map", map);
	}

	/*********************************
	 * stage 정보 삭제 폼.
	 *********************************/

	@RequestMapping("/producer/deleteStage")
	public String deletePremiumStage(int establishNum, String userId) {
		
		service.deletePremiumStage(establishNum, userId);

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		// 권한정보수정
		List<GrantedAuthority> list2 = (List<GrantedAuthority>) authentication.getAuthorities();

		// token 재생성 및 권한정보 재수정
		UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
				userService.selectMemberById(((User)authentication.getPrincipal()).getUserId()), null, list2);

		context.setAuthentication(newAuthentication);
		return "premiumStage/deleteSuccessView.tiles";
	}
	
	/********************************
	 * 	추가등록 페이지로 가는 Controller
	 ********************************/
	
	@RequestMapping("/producer/goAddPremiumStage")
	public ModelAndView goAddPremiumStagePage(String userId) {
		ModelAndView mav = mpc.profileInfo();
		
		mav.addObject("userId", userId);
		mav.setViewName("premiumStage/addPremiumStage.myTemp");
		
		return mav;
	}
	
	/*****************************
	 * 	PremiumStage 추가 등록
	 * @throws IOException 
	 * @throws IllegalStateException 
	 *****************************/
	
	@RequestMapping("/producer/addPremiumStage")
	public ModelAndView addPremiumStage(@ModelAttribute PremiumStage stage, HttpServletRequest request) throws IllegalStateException, IOException {
		
		ModelAndView mav = mpc.profileInfo();
		
		// Image 설정
		List<MultipartFile> list = stage.getMultiImage();
		List<String> imageList = new ArrayList<>();
		
		// 반복문으로 여러 개의 이미지를 업로드 시킨다. 이미지 supplierImage 폴더에 저장
		for(MultipartFile image : list) {
			int i = 0;
			String dir = request.getServletContext().getRealPath("/supplierImage");
			String fileName = UUID.randomUUID().toString();
			File upImage = new File(dir, fileName+".jpg");
			image.transferTo(upImage);
			if(i==0) {
				stage.setStageImage(fileName+".jpg");
				i=1;
			}
			imageList.add(fileName+".jpg");
		}
		
		// business service
		stage.setOperatorUserId(getUserId());
		service.addRegistStage(stage, imageList);
		
		// ModelAndView로 보내는 Map
		Map<String, Object> map = new HashMap<>();
		map.put("premiumStage", stage);
		map.put("imageList", imageList);
		
		mav.addObject("map",map);
		mav.setViewName("premiumStage/myStageDetailView.myTemp");
		
		return mav;
	}
	
	@RequestMapping("/selectPremiumStage")
	public ModelAndView viewPremiumStageList(HttpServletRequest request) {
		List<PremiumStage> list = null;
		Map<String, Object> map = null;
		
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// PremiumStage 정보를 모두 가져온다. (페이징? : 페이징을 하게되면 한줄에 3개씩해서 9개를 보여주자.)
		map = service.selectPremiumStage(page);
		list = (List<PremiumStage>)map.get("list");
		
		map.put("list", list);
		
		
		return new ModelAndView("premiumStage/premiumStageListView.tiles","map",map);
	}
	
	private String getUserId() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		return ((User)authentication.getPrincipal()).getUserId();
	}
	
	
}

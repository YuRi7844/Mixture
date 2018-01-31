package com.buskstop.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.HelpCommentService;
import com.buskstop.service.HelpService;
import com.buskstop.service.PerformanceCommentService;
import com.buskstop.service.PerformanceService;
import com.buskstop.service.PremiumStageReservationService;
import com.buskstop.service.PremiumStageService;
import com.buskstop.service.StageService;
import com.buskstop.service.UserService;
import com.buskstop.service.VideoCommentService;
import com.buskstop.service.VideoService;
import com.buskstop.vo.Help;
import com.buskstop.vo.HelpComment;
import com.buskstop.vo.Performance;
import com.buskstop.vo.PerformanceComment;
import com.buskstop.vo.PremiumStage;
import com.buskstop.vo.PremiumStageOption;
import com.buskstop.vo.Stage;
import com.buskstop.vo.StageImage;
import com.buskstop.vo.User;
import com.buskstop.vo.Video;
import com.buskstop.vo.VideoComment;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private StageService stageService;

	@Autowired
	private PremiumStageService premiumService;

	@Autowired
	private PremiumStageReservationService reservationService;
	
	@Autowired
	private PerformanceService performanceService;
	
	@Autowired
	private PerformanceCommentService performanceCommentService;
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private VideoCommentService videoCommentService;
	
	@Autowired
	private HelpService helpService;
	
	@Autowired HelpCommentService helpCommentService;

	/********************************* 회원관리 *********************************/

	@RequestMapping("member")
	public ModelAndView goMemberManagement() {
		return new ModelAndView("admin/memberManagementView.tiles", "list", userService.selectAllUser());
	}

	@RequestMapping("searchMember")
	public ModelAndView searchMemberManagement(String authority, String search) {
		return new ModelAndView("admin/memberManagementView.tiles", "list",
				userService.searchMemberManagement(authority, search));
	}

	@RequestMapping("dropUser")
	public ModelAndView dropUserManagement(String userId) {
		List<User> list = userService.dropAndSelectUser(userId);
		return new ModelAndView("admin/memberManagementView.tiles", "list", list);
	}

	/********************************* 공연장관리 *********************************/

	// 관리 페이지 메인.
	@RequestMapping("stage")
	public ModelAndView goStageManagement() {
		List<Stage> list = stageService.selectStage();
		for (Stage s : list) {
			System.out.println(s);
		}
		return new ModelAndView("admin/stageManagementView.tiles", "list", list);
	}

	// 관리자 공연 정보 수정
	@RequestMapping("updateStage")
	public ModelAndView updateAdminStageByStageNo(@RequestParam int stageNo) {
		Stage stage = stageService.selectStageByStageNo(stageNo);
		List<StageImage> stageImage = stageService.selectStageImageByStageNo(stageNo);
		stage.setStageImage(stageImage);
		return new ModelAndView("admin/adminStageUpdateView.tiles", "stage", stage);
	}

	// 관리자 공연정보 수정 후 관리자 공연관리 페이지로 이동.
	@RequestMapping("updateStageChange")
	public ModelAndView updateAdminStageChange(@ModelAttribute Stage stage, MultipartHttpServletRequest mhsq,
			HttpServletRequest request) throws IllegalStateException, IOException {
		// 공연장 변경
		stageService.updateStage(stage);

		// 파일 경로
		String dir = request.getServletContext().getRealPath("/stageImage");

		// 넘어온 파일을 리스트로 저장
		List<MultipartFile> mf = mhsq.getFiles("imgs");
		if (mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
		} else {
			for (int i = 0; i < mf.size(); i++) {
				// 파일 중복명 처리, 저장되는 파일 이름
				String fileName = UUID.randomUUID().toString();
				// 파일 저장
				File upImage = new File(dir, fileName + ".jpg");
				mf.get(i).transferTo(upImage);
				StageImage uploadImage = new StageImage(0, fileName, stage.getStageNo());
				stageService.insertStageImage(uploadImage);
			}
		}

		int delImageInt = 0;
		// 삭제한 이미지 삭제
		String[] delImage = request.getParameterValues("delImage");
		if (delImage != null) {
			for (int i = 0; i < delImage.length; i++) {
				delImageInt = Integer.parseInt(delImage[i]);
				stageService.deleteStageImageByStageImageNo(delImageInt);
			}
		}

		return new ModelAndView("redirect:/admin/stage.do");
	}

	// 관리자 공연장 삭제.
	@RequestMapping("deleteStage")
	public ModelAndView deleteAdminStage(@RequestParam int stageNo) {
		stageService.deleteStageImageByStageNo(stageNo);
		stageService.deleteStageByStageNo(stageNo);

		return new ModelAndView("redirect:/admin/stage.do");
	}

	// 관리자페이지 공연장 정보 조회
	@RequestMapping("stageSearch")
	public ModelAndView stageSearch(@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "reserve", required = false) String reserve,
			@RequestParam(value = "sDate", required = false, defaultValue = "1000-00-00") String sDate,
			@RequestParam(value = "eDate", required = false, defaultValue = "4000-00-00") String eDate,
			@RequestParam(value = "userId", required = false) String userId) throws ParseException {

		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);

		List<Stage> list = stageService.searchStageByAdmin(reserve, startDate, endDate, userId);
		return new ModelAndView("admin/stageManagementView.tiles", "list", list);
	}

	/********************************* 프리미엄 공연장관리 *********************************/

	@RequestMapping("premiumStage")
	public ModelAndView goPremiumStageManagement() {
		List<PremiumStage> list = premiumService.selectAllPremiumStage(); // premiumService

		return new ModelAndView("admin/premiumManagementView.tiles", "list", list);
	}

	@RequestMapping("searchPremiumStageAdmin")
	public ModelAndView searchPremiumStageManagement(
			@RequestParam(value = "category", defaultValue = "not") String category,
			@RequestParam(value = "search") String search) {

		if (category.equals("not")) {
			goPremiumStageManagement();
		}
		
		System.out.println("아아아아아아");

		// category 와 search를 매개변수로 가지는 service. (검색어에 맞게 premium stage list return)
		List<PremiumStage> list = premiumService.selectPremiumStageByAdmin(category, search);

		return new ModelAndView("admin/premiumManagementView.tiles", "list", list);
	}

	// 프리미엄공연장 상세보기
	@RequestMapping("premiumStageDetail")
	public ModelAndView premiumStageDetail(int establishNum) {
		
		PremiumStage stage = premiumService.viewByEstablishNum(establishNum);
		List<String> imageList = premiumService.selectImageLocation(establishNum);
		List<PremiumStageOption> optionList = reservationService.selectPremiumStageOptionByEstablishNo(establishNum);

		//test
		System.out.println(stage);
		for(String s : imageList) {
			System.out.println(s);
		}
		
		for(PremiumStageOption o : optionList) {
			System.out.println(o);
		}
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("premiumStage", stage);
		map.put("imageList", imageList);
		map.put("optionList", optionList);

		return new ModelAndView("admin/premiumStageDetailView.tiles", "map", map);
	}

	// 프리미엄 공연장 수정창으로 가는 controller
	@RequestMapping("goUpdateView")
	public ModelAndView adminUpdatePremiumView(int establishNum) {

		// 정보조회.
		PremiumStage stage = premiumService.viewByEstablishNum(establishNum);
		List<String> imageList = premiumService.selectImageLocation(establishNum);

		// list를 담을 map
		HashMap<String, Object> map = new HashMap<>();
		map.put("premiumStage", stage);
		map.put("imageList", imageList);

		return new ModelAndView("admin/adminUpdatePremiumView.tiles", "map", map);
	}

	@RequestMapping("updatePremium")
	public ModelAndView adminUpdatePremium(@ModelAttribute PremiumStage stage, HttpServletRequest request)
			throws IllegalStateException, IOException {

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
				if (i == 0) {
					// 첫번째 사진은 premium supplier의 대표사진으로 등록한다.
					stage.setStageImage(fileName + ".jpg");
					i = 1;
				}

				imageList.add(fileName + ".jpg");
			}
		}

		// service에서 처리해 줄것 : image 기존거 삭제 & 추가 / supplier 의 정보 update
		stage = premiumService.updatePremiumStage(stage.getEstablishNum(), imageList, stage);
		List<PremiumStageOption> optionList = reservationService.selectPremiumStageOptionByEstablishNo(stage.getEstablishNum());
		
		// map에 넣어서 보낸다.
		Map<String, Object> map = new HashMap<>();
		map.put("imageList", imageList);
		map.put("premiumStage", stage);
		map.put("optionList", optionList);
		
		return new ModelAndView("admin/premiumStageDetailView.tiles", "map", map);

	}

	// 삭제버튼 눌렀을 때.
	@RequestMapping("deletePremium")
	public ModelAndView adminDeletePremium(int establishNum, String userId) {
		// 삭제.
		premiumService.deletePremiumStage(establishNum, userId);
		// 리턴.
		return new ModelAndView("admin/premiumManagementView.tiles", "list", premiumService.selectAllPremiumStage());
	}

	/************************************ 공연정보 관리 ************************************/
	
	// 모든 일반 공연정보 controller
	@RequestMapping("performance")
	public ModelAndView performanceManagement() {
		List<Performance> list = performanceService.selectNormalPerformance();
		return new ModelAndView("admin/performanceManageView.tiles","list",list);
	}
	
	// 검색
	@RequestMapping("searchPerformance")
	public ModelAndView searchPerformanceManagement(
			@RequestParam(value="category") String category, 
			@RequestParam(value="search") String search, 
			@RequestParam(value="sDate", required=false) String sDate, 
			@RequestParam(value="eDate", required=false) String eDate) throws ParseException {
		
		if(sDate.equals("")) {
			sDate = "1000-00-00";
		}
		if (eDate.equals("")) {
			eDate = "3000-00-00";
		}
		
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		List<Performance> list = performanceService.selectPerformanceBySearch(category, search, startDate,endDate,0);
		return new ModelAndView("admin/performanceManageView.tiles","list", list);
	}
	
	// 상세보기
	@RequestMapping("viewDetailPerformance")
	public ModelAndView viewPerformance(int performanceNo) {
		Map<String, Object> map = new HashMap<>();
		
		Performance performance = performanceService.getPerformanceByPerformanceNo(performanceNo);
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String id = ((User)authentication.getPrincipal()).getUserId();
		
		map.put("performance", performance);
		map.put("userId", id);
		
		return new ModelAndView("performance/performanceDetailView.tiles","map",map);
	}
	
	// 수정화면으로 가기
	@RequestMapping("updateNormalPerformance")
	public ModelAndView goUpdatePerformance(int performanceNo) {
		Performance performance = performanceService.getPerformanceByPerformanceNo(performanceNo);
		return new ModelAndView("admin/updateNormalPerformance.tiles","performance",performance);
	}
	
	// 수정하기.
	@RequestMapping("updatePerformance")
	public ModelAndView updatePerformance(@ModelAttribute Performance performance, HttpServletRequest request) throws IllegalStateException, IOException {
		MultipartFile multiImage = performance.getMultiImage();
		if (multiImage != null && !multiImage.isEmpty()) {
			// 디렉토리
			String dir = request.getServletContext().getRealPath("/performanceImage");
			String fileName = UUID.randomUUID().toString();
			File upImage = new File(dir, fileName + ".jpg");
			multiImage.transferTo(upImage);
			performance.setPerformanceImage(fileName + ".jpg");
		}
		
		// update
		performanceService.updatePerformance(performance);
		performance = performanceService.getPerformanceByPerformanceNo(performance.getPerformanceNo());
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String id = ((User)authentication.getPrincipal()).getUserId();
		HashMap<String, Object> map = new HashMap<>();
		map.put("performance", performance);
		map.put("userId", id);
		
		return new ModelAndView("performance/performanceDetailView.tiles","map",map);
	}
	
	@RequestMapping("deleteNormalPerformance")
	public ModelAndView deleteNormalPerformance(int performanceNo) {
		performanceService.deletePerformanceByPerformance(performanceNo);
		List<Performance> list = performanceService.selectNormalPerformance();
		
		return new ModelAndView("admin/performanceManageView.tiles","list",list);
	}
	
	/******************************* 아티스트 공연정보 *******************************/
	
	@RequestMapping("adminPerformance")
	public ModelAndView artistPerformance() {
		List<Performance> list = performanceService.selectArtistPerformance();
		return new ModelAndView("admin/artistPerformanceManage.tiles","list",list);
	}
	
	@RequestMapping("viewDetailArtistPerformance")
	public ModelAndView artistPerformanceDetail(int performanceNo) {
		
		// performance
		Performance performance= performanceService.getPerformanceByPerformanceNo(performanceNo);
		
		// 이용자 id check
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String id = ((User)authentication.getPrincipal()).getUserId();

		// parameter map
		HashMap<String, Object> map = new HashMap<>();
		map.put("performance", performance);
		map.put("userId", id);
		return new ModelAndView("performance/artistPerformanceDetailView.tiles","map",map);
	}
	
	@RequestMapping("deleteArtistPerformance")
	public ModelAndView deleteArtistPerformance(int performanceNo) {
		performanceService.deletePerformanceByPerformance(performanceNo);
		List<Performance> list = performanceService.selectArtistPerformance();
		
		return new ModelAndView("admin/artistPerformanceManageView.tiles","list",list);
	}
	@RequestMapping("searchArtistPerformance")
	public ModelAndView searchArtistPerformanceManagement(
			@RequestParam(value="category") String category, 
			@RequestParam(value="search") String search, 
			@RequestParam(value="sDate", required=false) String sDate, 
			@RequestParam(value="eDate", required=false) String eDate) throws ParseException {
		
		if(sDate.equals("")) {
			sDate = "1000-00-00";
		}
		if (eDate.equals("")) {
			eDate = "3000-00-00";
		}
		
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		List<Performance> list = performanceService.selectPerformanceBySearch(category, search, startDate,endDate,1);
		return new ModelAndView("admin/artistPerformanceManageView.tiles","list", list);
	}
	
	/*********************** 영상관리 ***********************/
	
	// 카테고리 선택으로 이동하는 controller
	@RequestMapping("video")
	public String adminVideoMain() {
		return "admin/videoCategoryChoice.tiles";
	}
	// 관리를 할 video 목록으로 보내주는 controller
	@RequestMapping("videoList")
	public ModelAndView videoList(String category) {
		
		List<Video> list = videoService.viewAllVideo(category);
		return new ModelAndView("admin/videoManageView.tiles","list",list);
	}
	
	@RequestMapping("deleteVideo")
	public ModelAndView deleteVideo(int videoNo) {
		List<Video> list = videoService.deleteVideoAndSelect(videoNo);
		return new ModelAndView("admin/videoManageView.tiles","list",list);
	}
	
	/*********************** 영상 댓글 관리 ***********************/
	
	// 영상댓글 관리 창으로 이동하는 controller
	@RequestMapping("videoComment")
	public ModelAndView videoCommentView() {
		List<Video> list = videoService.selectVideo();
		return new ModelAndView("admin/videoCommentManage.tiles","list",list);
	}
	
	// 영상 번호로 댓글 목록을 보내는 controller (ajax)
	@RequestMapping("videoCommentList")
	public @ResponseBody List<VideoComment> returnComment(int videoNo,String userId, String content){
		System.out.println(videoNo);
		System.out.println(userId);
		System.out.println(content);
		List<VideoComment> list = videoCommentService.searchVideoCommentByAdmin(videoNo, userId, content); 
		return list;
	}
	
	@RequestMapping("deleteVideoComment")
	public ModelAndView deleteVideoComment(int videoCommentNo) {
		videoCommentService.deleteVideoCommentByVideoCommentNo(videoCommentNo);
		return videoCommentView();
	}
	
	/*********************** 공연정보 댓글 관리 ***********************/
	
	@RequestMapping("performanceComment")
	public ModelAndView performanceComment() {
		
		List<Performance> list = performanceService.selectAllPerfor(); 
		return new ModelAndView("admin/performanceCommentManage.tiles","list",list);
	}
	
	@RequestMapping("performanceCommentList")
	public @ResponseBody List<PerformanceComment> searchPerformanceComment(int performanceNo, String userId, String content) {
		List<PerformanceComment> list =  performanceCommentService.searchPerformanceComment(performanceNo, userId, content);
		return list; 
	}
	
	@RequestMapping("deletePerformanceComment")
	public ModelAndView deletePerformanceComment(int performanceCommentNo){
		performanceCommentService.deletePerformanceComment(performanceCommentNo);
		return performanceComment();
	}
	
	/************************ 고객센터 글 관리 *************************/
	/*@RequestMapping("/helpDetail")
	public ModelAndView helpDetail(@RequestParam int helpNum) {
		Help help = helpService.selectHelpByHelpNum(helpNum);
		System.out.println(help);
		String id = null;
		Map<String, Object> map = new HashMap<>();
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		id = ((User) authentication.getPrincipal()).getUserId();
		map.put("help", help);
		map.put("userId", id);
		
		List<HelpComment> list = helpCommentService.selectHelpCommentByHelpNo(helpNum);
		map.put("helpCommentList", list);

		return new ModelAndView("admin/helpDetailView.tiles", "map", map);
	}*/
	
	
	/************************ 고객센터 답글 관리 **************************/
	
	@RequestMapping("enterHelpComment")
	public ModelAndView enterHelpComment(@ModelAttribute HelpComment helpComment) {
		helpCommentService.insertHelpComment(helpComment);
		return new ModelAndView("redirect:/helpDetail.do", "helpNum", helpComment.getHelpNo());
	}
	
	@RequestMapping("removeHelpComment")
	public ModelAndView removeHelpComment(@RequestParam int helpCommentNo, @RequestParam int helpNo) {
		helpCommentService.deleteHelpComment(helpCommentNo);
		return new ModelAndView("redirect:/helpDetail.do", "helpNum", helpNo);
	}
	
	@RequestMapping("updateHelpComment")
	public ModelAndView updateHelpComment(@ModelAttribute HelpComment helpComment) {
		helpCommentService.updateHelpComment(helpComment);
		return new ModelAndView("redirect:/helpDetail.do", "helpNum", helpComment.getHelpNo());
	}
	
}

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

//github.com/um006500/busk-stop.git
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.ArtistService;
import com.buskstop.service.PerformanceLikeService;
import com.buskstop.service.PerformanceService;
import com.buskstop.vo.Artist;
import com.buskstop.vo.Performance;
import com.buskstop.vo.PerformanceLike;
import com.buskstop.vo.User;

@Controller
public class PerformanceController {

	@Autowired
	private PerformanceService service;

	@Autowired
	private PerformanceLikeService likeService;

	@Autowired
	private ArtistService artistService;

	@Autowired(required = true)
	private HttpServletRequest request;

	private String getUserId() {
		return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
	}

	/**************************** 공연정보 등록 ****************************/

	@RequestMapping("/member/performanceRegister")
	public ModelAndView insertPerformance(@ModelAttribute Performance performance, BindingResult r,
			HttpServletRequest request) throws IllegalStateException, IOException {

		System.out.println(r);
		System.out.println(r.getErrorCount());

		// 파일 업로드 처리
		MultipartFile multiImage = performance.getMultiImage();
		if (multiImage != null && !multiImage.isEmpty()) {
			// 디렉토리
			String dir = request.getServletContext().getRealPath("/performanceImage");
			String fileName = UUID.randomUUID().toString();
			File upImage = new File(dir, fileName + ".jpg");
			multiImage.transferTo(upImage);
			performance.setPerformanceImage(fileName + ".jpg");
		}
		performance.setPerformanceCode(0);
		service.insertPerformance(performance);

		return new ModelAndView("redirect:/selectPerformance.do");
	}

	@RequestMapping("/artist/performanceRegister")
	public ModelAndView insertArtistPerformance(@ModelAttribute Performance performance, HttpServletRequest request)
			throws IllegalStateException, IOException {
		// 파일 업로드 처리
		MultipartFile multiImage = performance.getMultiImage();
		if (multiImage != null && !multiImage.isEmpty()) {
			// 디렉토리
			String dir = request.getServletContext().getRealPath("/performanceImage");
			String fileName = UUID.randomUUID().toString();
			File upImage = new File(dir, fileName + ".jpg");
			multiImage.transferTo(upImage);
			performance.setPerformanceImage(fileName + ".jpg");
		}
		performance.setPerformanceCode(1);
		service.insertPerformance(performance);

		return new ModelAndView("redirect:/selectArtistPerformance.do");
	}

	// 이건 수정 화면에서 그 전에 입력 했던 내용들 불러오는거고
	@RequestMapping("/performanceUpdate3")
	public ModelAndView updatePerformance2(@RequestParam int performanceNo) {
		if (service.getPerformanceByPerformanceNo(performanceNo).getPerformanceUserId().equals(getUserId())) {
			Performance performance = service.getPerformanceByPerformanceNo(performanceNo);
			return new ModelAndView("update_performance.do", "Performance", performance);
		} else
			return new ModelAndView("member/performanceDetailView.do?performanceNo=" + performanceNo);
	}

	// 이건 수정하는 부분
	@RequestMapping("/performanceUpdate2")
	public ModelAndView updatePerformance(@ModelAttribute Performance performance, HttpServletRequest request)
			throws Exception {
		MultipartFile multiImage = performance.getMultiImage();
		if (multiImage != null && !multiImage.isEmpty()) {
			// 디렉토리
			String dir = request.getServletContext().getRealPath("/performanceImage");
			String fileName = UUID.randomUUID().toString();
			File upImage = new File(dir, fileName + ".jpg");
			multiImage.transferTo(upImage);
			performance.setPerformanceImage(fileName + ".jpg");
		}
		service.updatePerformance(performance);
		int performanceNo = performance.getPerformanceNo();
		return new ModelAndView("member/performanceDetailView.do?performanceNo=" + performanceNo, "performance", performance);
	}

	@RequestMapping("/deletePerformance")
	public String deletePerformance(@RequestParam int performanceNo) {
		if (service.getPerformanceByPerformanceNo(performanceNo).getPerformanceUserId().equals(getUserId())) {
			service.deletePerformanceByPerformance(performanceNo);
			return "selectPerformance.do";
		} else {
			String error = "performanceDetailView.do?performanceNo=" + performanceNo;
			return error;
		}
	}

	/*********************** 공연정보 조회 Controller ***********************/

	@RequestMapping("/selectPerformance")
	public ModelAndView selectAllPerformance(@RequestParam(required = false) String category,
			@RequestParam(required = false) String search, @RequestParam(required = false) String sDate,
			@RequestParam(required = false) String eDate) throws ParseException, IOException, ServletException {
		List<Performance> list = null;
		Map<String, Object> map = null;
		int page = 1;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {

		}
		if (category != null) { // 검색 시 페이징
			if (category.equals("title")) {
				map = service.selectPerformanceByPerformanceTitle(page, search);
			} else if (category.equals("user")) {
				map = service.selectPerformanceByPerformanceUserId(page, search);
			} else if (category.equals("location")) {
				map = service.selectPerformanceByPerformanceLocation(page, search);
			} else if (category.equals("name")) {
				map = service.selectPerformanceByPerformanceName(page, search);
			} else if (category.equals("content")) {
				map = service.selectPerformanceByPerformanceContent(page, search);
			} else if (category.equals("date")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

				Date startDate = transFormat.parse(sDate);
				Date endDate = transFormat.parse(eDate);

				map = service.selectPerformanceByPerformanceDate(page, startDate, endDate);
			}
		} else {
			map = service.selectPerformance(page);
			category = "title";
			search = "";
			sDate = "";
			eDate = "";
		}
		list = (List<Performance>) map.get("list");
		list = likeCounter(list);

		map.put("list", list);
		map.put("search", search);
		map.put("category", category);
		map.put("sDate", sDate);
		map.put("eDate", eDate);

		return new ModelAndView("performance/performanceView.tiles", "map", map);
	}

	/*********************** 아티스트 공연정보 조회 Controller ***********************/

	@RequestMapping("/selectArtistPerformance")
	public ModelAndView selectArtistPerformance(@RequestParam(required = false) String category,
			@RequestParam(required = false) String search, @RequestParam(required = false) String sDate,
			@RequestParam(required = false) String eDate) throws ParseException, IOException, ServletException {
		List<Performance> list = null;
		Map<String, Object> map = null;
		int page = 1;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {

		}
		if (category != null) { // 검색 시 페이징
			if (category.equals("title")) {
				map = service.selectArtistPerformanceByPerformanceTitle(page, search);
			} else if (category.equals("user")) {
				map = service.selectArtistPerformanceByPerformanceUserId(page, search);
			} else if (category.equals("location")) {
				map = service.selectArtistPerformanceByPerformanceLocation(page, search);
			} else if (category.equals("name")) {
				map = service.selectArtistPerformanceByPerformanceName(page, search);
			} else if (category.equals("content")) {
				map = service.selectArtistPerformanceByPerformanceContent(page, search);
			} else if (category.equals("date")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

				Date startDate = transFormat.parse(sDate);
				Date endDate = transFormat.parse(eDate);

				map = service.selectArtistPerformanceByPerformanceDate(page, startDate, endDate);
			}
		} else {
			map = service.selectArtistPerformance(page);
			category = "title";
			search = "";
			sDate = "";
			eDate = "";
		}
		list = (List<Performance>) map.get("list");
		list = likeCounter(list);

		map.put("list", list);
		map.put("search", search);
		map.put("category", category);
		map.put("sDate", sDate);
		map.put("eDate", eDate);

		return new ModelAndView("performance/artistPerformanceView.tiles", "map", map);
	}

	/******************* 좋아요 *******************/

	// 좋아요 갯수 조회하는 메서드
	public List<Performance> likeCounter(List<Performance> list) {

		List<PerformanceLike> likeList = likeService.selectAllPerformanceLike();

		int count = 0;
		for (Performance pf : list) {
			for (PerformanceLike pl : likeList) {
				if (pf.getPerformanceNo() == pl.getPerformanceLikeNo()) {
					count++;
					pf.setLikeCount(count);
				}
			}
			count = 0;
		}

		return list;
	}

	/****************************** 상세보기 ******************************/

	@RequestMapping("/member/performanceDetailView")
	public ModelAndView performanceDetailView(@RequestParam int performanceNo) {

		// Performance 정보 조회
		service.updatePerformanceCountByPerformanceNo(performanceNo); // 조회수+1 호출
		Performance performance = service.getPerformanceByPerformanceNo(performanceNo);
		List<Performance> list = new ArrayList<Performance>();
		list.add(performance);
		list = likeCounter(list);
		performance = list.get(0);

		String id = null;
		// 값으로 보낼 map
		Map<String, Object> map = new HashMap<>();

		try {
			// 접속한 사용자의 id값 조회
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();
			id = ((User) authentication.getPrincipal()).getUserId();
		} catch (Exception e) {
			// 사용자가 로그아웃 상태일 경우
			id = "0";
		} finally {
			map.put("performance", performance);
			map.put("userId", id);
		}

		return new ModelAndView("performance/performanceDetailView.tiles", "map", map);
	}

	@RequestMapping("/artistPerformanceDetailView")
	public ModelAndView artistPerformanceDetailView(@RequestParam int performanceNo) {
		service.updatePerformanceCountByPerformanceNo(performanceNo); // 조회수+1 호출

		// performance 정보 조회
		Performance performance = service.getPerformanceByPerformanceNo(performanceNo);
		List<Performance> list = new ArrayList<Performance>();
		list.add(performance);
		list = likeCounter(list);
		performance = list.get(0);

		// 아티스트 정보 조회
		Artist artist = artistService.readArtistByUserId(performance.getPerformanceUserId());

		// 사용자 id 체크
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String id = "";
		
		try {
			if(authentication.getPrincipal()!=null) {
				id = ((User) authentication.getPrincipal()).getUserId();
			}
		} catch(Exception e) {
			
		}

		// attribute 로 보낼 Map 생성.
		Map<String, Object> map = new HashMap<>();
		map.put("performance", performance);
		map.put("artist", artist);
		map.put("userId", id);

		return new ModelAndView("performance/artistPerformanceDetailView.tiles", "map", map);
	}

}

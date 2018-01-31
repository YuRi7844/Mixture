package com.buskstop.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.ArtistService;
import com.buskstop.service.PerformanceService;
import com.buskstop.service.PremiumStageService;
import com.buskstop.service.VideoService;
import com.buskstop.vo.Performance;
import com.buskstop.vo.PremiumStage;
import com.buskstop.vo.Video;

@Controller
public class mainPageController {
	
	@Autowired
	private PerformanceService performanceService;
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private PremiumStageService premiumService;
	
	@RequestMapping("/index")
	public ModelAndView goIndex() {
		
		HashMap<String, Object> map = new HashMap<>();
	
		// PerformanceService
		// VideoService <-- 좋아요 수로 orderby 해서 조회.
		List<Performance> performancelist = performanceService.selectTopLikePerformance();
		List<Video> videoList = videoService.selectTopLikeVideo();
		List<PremiumStage> premiumList = premiumService.mainPremiumStage();
		
		/*for(PremiumStage g : premiumList) {
			System.out.println(g);
		}*/
		
		// scope에 map 넣기.
		if(artistService.recommendArtist() != null) {
			map.putAll(artistService.recommendArtist());
		} 
		map.put("performance", performancelist);
		map.put("video", videoList);
		map.put("premium", premiumList);
		return new ModelAndView("main.tiles","map",map);
	}
	
	
	
	
}

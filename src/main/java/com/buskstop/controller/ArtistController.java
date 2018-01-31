package com.buskstop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.ArtistService;
import com.buskstop.service.FollowService;
import com.buskstop.service.PerformanceService;
import com.buskstop.service.VideoService;
import com.buskstop.vo.Artist;
import com.buskstop.vo.Follow;
import com.buskstop.vo.Performance;
import com.buskstop.vo.User;
import com.buskstop.vo.Video;

@Controller
public class ArtistController {

	@Autowired
	private ArtistService service;
	
	@Autowired
	private PerformanceService performanceService;
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private FollowService followService;
	
	@RequestMapping("/artist/readArtist")
	public ModelAndView readArtist() {
		SecurityContext context = SecurityContextHolder.getContext();
		// SecurityContext 객체에서 Authentication(인증내용)을 받아온다.
		Authentication authentication = context.getAuthentication();
		String userId = ((User)authentication.getPrincipal()).getUserId();
		Artist artist = service.readArtistByUserId(userId);
		return new ModelAndView("video/videoRegisterArtistView.tiles", "artist", artist);
	}
	
	@RequestMapping("/artistInfo")
	public ModelAndView artistDetail(String artistId) {
		
		// 아티스트 상세정보 조회시에 필요한 정보들 조회. 
		Artist artist = service.readArtistByUserId(artistId);
		List<Video> videoList = videoService.viewVideoByArtist(artist.getArtistId());
		List<Performance> performanceList = performanceService.selectPerformanceById(artistId);
		List<Follow> follower = followService.selectFollowByFollowerId(artistId);
		int followerCount = follower.size();
		
		// 사용자의 id 조회
		SecurityContext context = SecurityContextHolder.getContext();
		// SecurityContext 객체에서 Authentication(인증내용)을 받아온다.
		Authentication authentication = context.getAuthentication();
		String userId = "";
		try {
			if(authentication.getPrincipal()!=null) {
				userId = ((User) authentication.getPrincipal()).getUserId();
			}
		}catch(Exception e) {
		}
		
		
		// 정보들을 Map에 담아서 전달.
		Map<String,Object> map = new HashMap<>();
		map.put("artist", artist);
		map.put("videoList", videoList);
		map.put("performanceList", performanceList);
		map.put("userId", userId);
		map.put("followerCount", followerCount);
		
		return new ModelAndView("artist/artistInfomationView.tiles","map",map);
	}
}

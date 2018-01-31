package com.buskstop.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.VideoService;
import com.buskstop.vo.User;
import com.buskstop.vo.Video;
import com.buskstop.vo.VideoLike;

@Controller
public class VideoController {

	@Autowired
	private VideoService service;

	// 조회수 업로드
	@RequestMapping("")
	public String uploadVideoHits(int videoNo) {
		
		// 조회수를 update 하는 service 구현.
		
		return null;
	}
	
	//좋아요
	@RequestMapping("/member/videoLike")
	public @ResponseBody String videoLike(@RequestParam int videoNo) {
		//test
		System.out.println(videoNo);
		
		List<VideoLike> list = service.selectLikeUserByNum(videoNo);
		VideoLike like = new VideoLike(videoNo, getUserId());
		for(VideoLike v : list) {
			if(v.getVideoLikeUserId().equals(getUserId())) {
				service.deleteLike(like);
				return "0";
			}
		}
		
		service.plusLike(like);
		return "1";
		
	}

	@RequestMapping("/member/likeCheck")
	public @ResponseBody String likeCheck(int videoNo) {
		
		// 좋아요한 회원정보 조회 (없으면 0, 있으면 1)
		List<VideoLike> list = service.selectLikeUserByNum(videoNo); // 1은 영상번호
		String num = "0";
		
		// 좋아요정보가 없으면 return 0.
		if(list==null && list.size()==0) {
			return num;
		}
		// user-check
		for (VideoLike vl : list) {
			System.out.println(vl);
			if (vl.getVideoLikeUserId().equals(getUserId())) {
				num = "1";
			}
		}
		System.out.println(num);
		return num;
	}

	private String getUserId() {
		return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
	}
	
	/* ----------------- 영상 -----------------------*/
	
	@RequestMapping("/createVideo")
	public ModelAndView createVideo(@ModelAttribute Video video) {
		service.insertVideo(video);
		return new ModelAndView("redirect:/readVideoByVideoNo.do", "videoNo", video.getVideoNo());

	}
	
	/**
	 * 영상 번호로 영상 상세보기
	 * @param videoNo
	 * @return
	 */
	@RequestMapping("/readVideoByVideoNo")
	public ModelAndView readVideoByVideoNo(@RequestParam int videoNo) {
		Video video = service.viewVideoByVideoNo(videoNo);
		
		SecurityContext context = SecurityContextHolder.getContext();
		// SecurityContext 객체에서 Authentication(인증내용)을 받아온다.
		Authentication authentication = context.getAuthentication();
		
		String userId = null;
		
		try {
			userId = ((User) authentication.getPrincipal()).getUserId();
		} catch (Exception e) {
			userId = "";
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("video", video);
		map.put("userId", userId);
		return new ModelAndView("video/videoDetailView.tiles", "map", map);
	}

	/**
	 * 영상등록에사 공연영상 카테고리 선택
	 * 
	 * @return
	 */
	@RequestMapping("/member/selectPerformanceVideoCategory")
	public ModelAndView selectPerformanceVideoCategory() {
		SecurityContext context = SecurityContextHolder.getContext();
		// SecurityContext 객체에서 Authentication(인증내용)을 받아온다.
		Authentication authentication = context.getAuthentication();
		String userId = ((User) authentication.getPrincipal()).getUserId();
		return new ModelAndView("video/videoRegisterPerformanceView.tiles", "userId", userId);
	}

	/**
	 * 영상등록에서 개인연습영상 카테고리 선택
	 * 
	 * @return
	 */
	@RequestMapping("/member/selectMemberVideoCategory")
	public ModelAndView selectMemberVideoCategory() {
		SecurityContext context = SecurityContextHolder.getContext();
		// SecurityContext 객체에서 Authentication(인증내용)을 받아온다.
		Authentication authentication = context.getAuthentication();
		String userId = ((User) authentication.getPrincipal()).getUserId();
		return new ModelAndView("video/videoRegisterPracticeView.tiles", "userId", userId);
	}

	/**
	 * 영상등록에서 아티스트 카테고리 선택
	 * @return
	 */
	@RequestMapping("/artist/selectArtistVideoCategory")
	public ModelAndView selectArtistVideoCategory() {
		SecurityContext context = SecurityContextHolder.getContext();
		// SecurityContext 객체에서 Authentication(인증내용)을 받아온다.
		Authentication authentication = context.getAuthentication();

		String userId = ((User) authentication.getPrincipal()).getUserId();
		return new ModelAndView("video/videoRegisterArtistView.tiles", "userId", userId);
	}


	//영상 목록에서 카테고리 선택
	@RequestMapping("/videoListCategory")
	public ModelAndView videoList(@RequestParam String category, HttpServletRequest request) {
		// category를 매개변수로 받아서 해당 카테고리의 Video 객체를 list로 받아온다.
		List<Video> list = service.viewAllVideo(category);
		// response
		if(category.equals("performance")) {
			return new ModelAndView("video/userPerformanceVideoListView.tiles","list",list);
		}else if (category.equals("artist")) {
			return new ModelAndView("video/artistVideoListView.tiles","list",list);
		}else {
			return new ModelAndView("video/userPracticeVideoListView.tiles","list",list);
		}
	}
	
	// 영상 수정 선택
	@RequestMapping("/videoChangeInfoView")
	public ModelAndView changeInfoView(int videoNo) {
		Video video = service.viewVideoByVideoNo(videoNo);
		return new ModelAndView("video/videoChangeInfoView.tiles","video",video);
	}
	
	//영상 수정 후 영상 상세보기 화면으로
	@RequestMapping("/updateVideoInfo")
	public ModelAndView updateArtistVideo(@ModelAttribute Video video) {
		// Artist 공연영상일 경우에는 artistVideo 정보수정 controller 로 보낸다.
		if (video.getVideoCategory().equals("artist")) {
			return new ModelAndView("redirect:/artist/updateVideoInfo.do", "video", video);
		}

		// test용 출력
		System.out.println(video);

		// video정보를 수정하는 update service
		service.updateVideo(video);
		video = service.viewVideoByVideoNo(video.getVideoNo());
		
		// response
		return new ModelAndView("redirect:/readVideoByVideoNo.do", "videoNo", video.getVideoNo());
	}
	
	//영상 수정인데 아티스트인 경우 여기로 이동 후 상세보기로.
	@RequestMapping("/artist/updateVideoInfo")
	public ModelAndView updatePerformanceVideo(@ModelAttribute Video video) {
		service.updateVideo(video);
		video = service.viewVideoByVideoNo(video.getVideoNo());
		return new ModelAndView("video/videoDetailView.tiles", "video", video);
	}
	
	// 영상 삭제
	@RequestMapping("/deleteVideo")
	public ModelAndView deleteVideo(int videoNo, String category, HttpServletRequest request) {
		service.deleteVideoByVideoNum(videoNo);
		return videoList(category, request);
	}
	
	//-------------영상 검색조건으로 조회 -----------------//
	//개인연습영상 카테고리에서 제목으로 영상 조회
	@RequestMapping("/viewPracticeVideoListByTitle")
	public ModelAndView viewPracticeVideoListByTitle(@RequestParam String category, 
											 @RequestParam String filter, 
											 @RequestParam String search) {
		List<Video> list = null;
		// response
		if(filter.equals("title")) {
			list = service.viewVideoByTitleAndCategory(category, search);
			return new ModelAndView("video/userPracticeVideoListView.tiles","list",list);
		}else if (filter.equals("userId")) {
			list = service.viewVideoByUserIdAndCategory(category, search);
			return new ModelAndView("video/userPracticeVideoListView.tiles","list",list);
		}else if (filter.equals("artist")) {
			list = service.viewVideoByArtistAndCategory(category, search);
			return new ModelAndView("video/userPracticeVideoListView.tiles","list",list);
		}else {
			list = service.viewVideoByContentAndCategory(category, search);
			return new ModelAndView("video/userPracticeVideoListView.tiles","list",list);
		}
	}
	
		//개인연습영상 카테고리에서 제목으로 영상 조회
		@RequestMapping("/viewArtistVideoListByTitle")
		public ModelAndView viewArtistVideoListByTitle(@RequestParam String category, 
												 @RequestParam String filter, 
												 @RequestParam String search) {
			List<Video> list = null;
			// response
			if(filter.equals("title")) {
				list = service.viewVideoByTitleAndCategory(category, search);
				return new ModelAndView("video/artistVideoListView.tiles","list",list);
			}else if (filter.equals("userId")) {
				list = service.viewVideoByUserIdAndCategory(category, search);
				return new ModelAndView("video/artistVideoListView.tiles","list",list);
			}else if (filter.equals("artist")) {
				list = service.viewVideoByArtistAndCategory(category, search);
				return new ModelAndView("video/artistVideoListView.tiles","list",list);
			}else {
				list = service.viewVideoByContentAndCategory(category, search);
				return new ModelAndView("video/artistVideoListView.tiles","list",list);
			}
		}
		
		//공연 카테고리에서 제목으로 영상 조회
		@RequestMapping("/viewPerformanceVideoListByTitle")
		public ModelAndView viewPerformanceVideoListByTitle(@RequestParam String category, 
												 @RequestParam String filter, 
												 @RequestParam String search) {
			List<Video> list = null;
			// response
			if(filter.equals("title")) {
				list = service.viewVideoByTitleAndCategory(category, search);
				return new ModelAndView("video/userPerformanceVideoListView.tiles","list",list);
			}else if (filter.equals("userId")) {
				list = service.viewVideoByUserIdAndCategory(category, search);
				return new ModelAndView("video/userPerformanceVideoListView.tiles","list",list);
			}else if (filter.equals("artist")) {
				list = service.viewVideoByArtistAndCategory(category, search);
				return new ModelAndView("video/userPerformanceVideoListView.tiles","list",list);
			}else {
				list = service.viewVideoByContentAndCategory(category, search);
				return new ModelAndView("video/userPerformanceVideoListView.tiles","list",list);
			}
		}
		
		@RequestMapping("/updateVideoHits")
		public @ResponseBody String updateVideoHits(int videoNo) {
			Video video = service.viewVideoByVideoNo(videoNo);
			video.setVideoHits(video.getVideoHits()+1);
			service.updateVideo(video);
			
			return "success";
		}
	
}

package com.buskstop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buskstop.service.FollowService;
import com.buskstop.vo.Follow;

@Controller
public class FollowController {
	
	@Autowired
	private FollowService service;
	
	/******************** 팔로우 & 언팔로우 ********************/
	
	@RequestMapping("/member/follow")
	public @ResponseBody String follow(String userId, String artistId) {
		System.out.println(userId+":follow.do");
		System.out.println(artistId);
		service.doFollow(userId, artistId);
		return "follow";
	}
	@RequestMapping("/member/unfollow")
	public @ResponseBody String followCancle(String userId, String artistId) {
		System.out.println(userId+"unfollow.do");
		System.out.println(artistId);
		service.unFollow(userId, artistId);
		return "unfollow";
	}

	/******************************************************/
	
	/**
	 * 팔로우 정보를 조회해서 팔로우 여부를 알려준다. (ajax)
	 * @return
	 */
	@RequestMapping("/searchFollow")
	public @ResponseBody String searchFollw(String userId, String artistId) {
		if(service.searchFollowing(userId, artistId)) {
			// 팔로우 정보가 있으면?
			return "follow";
		} else {
			// 없으면?
			return "notFollow";
		}
	}
}

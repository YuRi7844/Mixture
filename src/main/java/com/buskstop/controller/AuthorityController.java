package com.buskstop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.AuthorityService;
import com.buskstop.vo.User;

@Controller
public class AuthorityController {

	@Autowired
	AuthorityService service;
	
	@RequestMapping("/readArtistAuthorityByUserId")
	public ModelAndView readArtistAuthorityByUserId() {
		SecurityContext context = SecurityContextHolder.getContext();
		// SecurityContext 객체에서 Authentication(인증내용)을 받아온다.
		Authentication authentication = context.getAuthentication();
		String userId = ((User)authentication.getPrincipal()).getUserId();
		boolean flag = service.readArtistAutoritiesByUserId(userId);
		if(flag) {
			return new ModelAndView("video/videoRegisterArtistView.tiles", "videoArtistCategory", flag);
		}else {
			return new ModelAndView("video/videoRegisterArtistView.tiles", "videoArtistCategory", flag);
		}
		
	}
	
	
}

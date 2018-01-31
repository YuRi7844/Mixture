
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

import com.buskstop.service.HelpCommentService;
import com.buskstop.service.HelpService;
import com.buskstop.vo.Help;
import com.buskstop.vo.HelpComment;
import com.buskstop.vo.Performance;
import com.buskstop.vo.User;

@Controller
public class HelpController {

	@Autowired
	private HelpService service;
	
	@Autowired
	private HelpCommentService helpCommentService;
	
	@Autowired
	private HttpServletRequest request;
	
	/*
	 * @RequestMapping("/helpRegister") public ModelAndView
	 * insertPerformance(@ModelAttribute Help help, HttpServletRequest request)
	 * throws IllegalStateException, IOException { //파일 업로드 처리 MultipartFile
	 * multiImage = help.getMultiImage(); if(multiImage!=null &&
	 * !multiImage.isEmpty()) { //디렉토리 String dir =
	 * request.getServletContext().getRealPath("/helpImage"); String fileName =
	 * UUID.randomUUID().toString(); File upImage = new File(dir, fileName+".jpg");
	 * multiImage.transferTo(upImage); help.setHelpImage(fileName+".jpg"); }
	 * help.setHelpCode(0); service.insertPerformance(help);
	 * 
	 * return new ModelAndView("redirect:/selectPerformance.do"); }
	 */

	@RequestMapping("/helpRegister")
	public ModelAndView insertHelp(@ModelAttribute Help help,HttpServletRequest request) throws IllegalStateException, IOException {
		// 파일 업로드 처리
		String dir = request.getServletContext().getRealPath("/helpImage");
		String fileName = null;
		File upImage = null;
		
		MultipartFile multiImage = help.getMultiImage();
		if(multiImage!=null && !multiImage.isEmpty()) {
			fileName = UUID.randomUUID().toString();
			upImage = new File(dir, fileName+".jpg");
			multiImage.transferTo(upImage);
			help.setHelpImage(fileName+".jpg");
		}
		
		MultipartFile multiImage2 = help.getMultiImage2();
		if(multiImage2!=null && !multiImage2.isEmpty()) {
			fileName = UUID.randomUUID().toString();
			upImage = new File(dir, fileName+".jpg");
			multiImage2.transferTo(upImage);
			help.setHelpImage2(fileName+".jpg");
		}
		service.insertHelp(help);
		return new ModelAndView("/selectHelp.do");
	}

	@RequestMapping("/helpDetail")
	public ModelAndView helpDetail(@RequestParam int helpNum) {
		Help help = service.selectHelpByHelpNum(helpNum);
//		System.out.println(help);
		String id = null;
		Map<String, Object> map = new HashMap<>();
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		id = ((User) authentication.getPrincipal()).getUserId();
		map.put("help", help);
		map.put("userId", id);
		
		if(helpCommentService.selectHelpCommentByHelpNo(helpNum)==null) {
			return new ModelAndView("help/helpDetailView.tiles","map",map);
		}
		
		List<HelpComment> list = helpCommentService.selectHelpCommentByHelpNo(helpNum);
		map.put("helpCommentList", list);
		return new ModelAndView("help/helpDetailView.tiles", "map", map);
	}
	
	@RequestMapping("/deleteHelp")
	public String deleteHelp(@RequestParam int helpNum) {
		service.deleteHelpByHelpNum(helpNum);
		return "/selectHelp.do"; // 여기엔 목록으로 이동 
	}
	
	@RequestMapping("/updateHelp")
	public ModelAndView udpateHelp(@ModelAttribute Help help) throws IllegalStateException, IOException {
		
		String dir = request.getServletContext().getRealPath("/helpImage");
		String fileName = UUID.randomUUID().toString();
		File upImage = null;
		
		MultipartFile multiImage = help.getMultiImage();
		if(multiImage!=null && !multiImage.isEmpty()) {
			//디렉토리
			upImage = new File(dir, fileName+".jpg");
			multiImage.transferTo(upImage);
			help.setHelpImage(fileName+".jpg");
		}
		
		MultipartFile multiImage2 = help.getMultiImage2();
		if(multiImage2!=null && !multiImage2.isEmpty()) {
			//디렉토리
			upImage = new File(dir, fileName+".jpg");
			multiImage2.transferTo(upImage);
			help.setHelpImage2(fileName+".jpg");
		}
		
		service.updateHelp(help);
		int helpNum = help.getHelpNum();
		return new ModelAndView("redirect:/helpDetail.do?helpNum="+helpNum,"help",help);
				
	}
	
	@RequestMapping("/updateHelp2")
	public ModelAndView updateHelp2(@RequestParam int helpNum) {
		Help help = service.selectHelpByHelpNum(helpNum);
		return new ModelAndView("help/helpUpdate.tiles","help",help);
	}
	
	@RequestMapping("/selectHelp")
	public ModelAndView viewAllHelp(@RequestParam(required = false) String category, @RequestParam(required = false) String search) {
		int page = 1;
		Map<String, Object> map = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {}
		
		if(category != null) {
			if(category.equals("title")) {
				map = service.selectHelpByHelpTitle(page, search);
			}else if(category.equals("content")) {
				map = service.selectHelpByHelpContent(page, search);
			}else if(category.equals("user")) {
				map = service.selectHelpByHelpUserId(page, search);
			}
		} else {
			map = service.selectAllHelp(page);
			category = "title";
			search = "";
		}
		
		map.put("search", search);
		map.put("category", category);
		
		return new ModelAndView("help/helpView.tiles", "map", map);
	}
	
	
	
	
	
	
	
	
	
	
	
}

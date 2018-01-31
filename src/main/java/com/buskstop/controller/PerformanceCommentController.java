package com.buskstop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buskstop.service.PerformanceCommentService;
import com.buskstop.vo.PerformanceComment;
import com.buskstop.vo.User;

@Controller
public class PerformanceCommentController {
	@Autowired
	private PerformanceCommentService service;
	
	@RequestMapping("/performanceCommentList")
	@ResponseBody
	public List<PerformanceComment> performanceCommentList(@RequestParam int performanceNo) throws Exception {
		List<PerformanceComment> list = service.listComment(performanceNo);
		return list;
	}
	
	@RequestMapping("/performanceCommentInsert")
	@ResponseBody
	public String insertPerformanceComment(int performanceNo, String performanceComment) throws Exception {
		if(performanceComment.equals("")) {
			return "empty";
		}
		PerformanceComment pComment = new PerformanceComment(performanceNo,performanceComment);
		// 인증해서 아이디값 세팅
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		pComment.setPerformanceCommentUserId(((User)authentication.getPrincipal()).getUserId());
		service.insertPerformanceComment(pComment);
		return "success";
	}
	
	@RequestMapping("/performanceCommentUpdate")
	@ResponseBody
	public String updatePerformanceComment(int performanceCommentNo ,String UpdatePerformanceComment) {
		if(UpdatePerformanceComment.equals("")) {
			return "empty";
		}
		PerformanceComment pComment2 = new PerformanceComment();
		pComment2.setPerformanceCommentNo(performanceCommentNo);
		pComment2.setPerformanceComment(UpdatePerformanceComment);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		pComment2.setPerformanceCommentUserId(((User)authentication.getPrincipal()).getUserId());
		service.updatePerformanceComment(pComment2);
		return "success";
	}
	
	@RequestMapping("/performanceCommentDelete")
	@ResponseBody
	public String deletePerformanceComment(int performanceCommentNo) {
		service.deletePerformanceCommentByPerformanceCommentNo(performanceCommentNo);
		return "redirect:/allSelectPerformance.do";
	}
}

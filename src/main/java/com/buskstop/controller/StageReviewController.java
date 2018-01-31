package com.buskstop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buskstop.service.StageReviewService;
import com.buskstop.vo.StageReview;
import com.buskstop.vo.User;

@Controller
public class StageReviewController {
	
	@Autowired
	private StageReviewService service;
	
	@RequestMapping("/stageCommentList")
	@ResponseBody
	public List<StageReview> stageCommentList(@RequestParam int stageNo){
//	System.out.println("리스트 할로");
	List<StageReview> list = service.listComment(stageNo);
	
//	System.out.println("------"+list);
	return list;
	}
	
	@RequestMapping("/stageCommentInsert")
	@ResponseBody
	public String insertStageComment(@RequestParam int stageNo, @RequestParam String stageComment, @RequestParam int starScore) {
		if(stageComment.equals("")) {
			return "empty";
		}
		StageReview sComment = new StageReview(stageNo, starScore,stageComment);
		// 인증해서 아이디값 세팅
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		sComment.setStageCommentUserId(((User)authentication.getPrincipal()).getUserId());
		service.insertStageComment(sComment);
		
		return "success";
//	System.out.println(sComment.getStarScore());
//	System.out.println(sComment);
	}
	
	@RequestMapping("/stageCommentUpdate")
	@ResponseBody
	public void updateStageComment(String stageCommentUserId, String UpdateStageComment) {
//		System.out.println("수정 오나요 컨트롤러까지"+stageCommentUserId);
		StageReview sComment2 = new StageReview();
		sComment2.setStageCommentUserId(stageCommentUserId);
		sComment2.setStageComment(UpdateStageComment);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		sComment2.setStageCommentUserId(((User)authentication.getPrincipal()).getUserId());
		
		service.updateStageComment(sComment2);		
	}
	
	@RequestMapping("/stageCommentDelete")
	@ResponseBody
	public String deleteStageComment(String stageCommentUserId) {
//		System.out.println("삭제 오나요 컨트롤러까지");
		service.deleteStageCommentByStageCommentUserId(stageCommentUserId);
		return "redirect:/allSelectStage.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

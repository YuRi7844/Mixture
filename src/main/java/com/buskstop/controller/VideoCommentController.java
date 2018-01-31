package com.buskstop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.VideoCommentService;
import com.buskstop.vo.User;
import com.buskstop.vo.VideoComment;

@Controller
public class VideoCommentController {

	@Autowired
	private VideoCommentService service;
	
	/**
	 * 댓글입력
	 * @param videoNo
	 * @param videoComment
	 * @return
	 */
	@RequestMapping("/member/enterVideoComment")
	public @ResponseBody List<VideoComment> enterVideoComment(int videoNo,String videoComment){
		// 댓글 객체 생성
		VideoComment comment = new VideoComment(videoNo, videoComment);
		
		// 권한정보에서 id 이용해서 객체값 setting
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		comment.setVideoCommentUserId(((User)authentication.getPrincipal()).getUserId());
		
		// businesslogic (댓글정보 insert)
		service.insertVideoComment(comment);
		
		//response
		return service.selectVideoCommentByVideoNo(videoNo);
	}
	
	/**
	 * 댓글 조회
	 * @param videoNo
	 * @return
	 */
	@RequestMapping("/readVideoComment")
	public @ResponseBody List<VideoComment> readVideoComment(@RequestParam int videoNo){
		List<VideoComment> list = service.selectVideoCommentByVideoNo(videoNo);
		return list;
	}
	
	@RequestMapping("/member/editVideoComment")
	@ResponseBody
	public void updateVideoComment(@ModelAttribute VideoComment videoComment) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		videoComment.setVideoCommentUserId(((User)authentication.getPrincipal()).getUserId());
		service.updateVideoCommentByVideoCommentNo(videoComment);
	}
	
	@RequestMapping("/member/deleteComment")
	@ResponseBody
	public void deleteComment(@RequestParam int videoCommentNo) {
		System.out.println("삭제 들어옴?");
		service.deleteVideoCommentByVideoCommentNo(videoCommentNo);
	}
}

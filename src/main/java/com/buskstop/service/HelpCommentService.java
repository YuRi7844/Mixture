package com.buskstop.service;

import java.util.List;

import com.buskstop.vo.HelpComment;

public interface HelpCommentService {
	
int insertHelpComment(HelpComment helpComment);
	
	int deleteHelpComment(int helpCommentNo);
	
	int updateHelpComment(HelpComment helpComment);
	
	/**
	 * 답글 전체조회
	 * @return
	 */
	List<HelpComment> selectAllHelpComment();
	
	/**
	 * 질문자 id로 조회
	 * @param helpUserId
	 * @return
	 */
	List<HelpComment> selectHelpCommentByUserId(String helpUserId);
	
	/**
	 * 답글 내용으로 조회
	 * @param search
	 * @return
	 */
	List<HelpComment> selectHelpCommentByComment(String search);
	
	/**
	 * 게시글 번호로 조회
	 * @param helpNo
	 * @return
	 */
	List<HelpComment> selectHelpCommentByHelpNo(int helpNo);
}

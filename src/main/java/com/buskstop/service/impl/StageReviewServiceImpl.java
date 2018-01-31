package com.buskstop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buskstop.dao.StageReviewDao;
import com.buskstop.service.StageReviewService;
import com.buskstop.vo.StageReview;

@Service
public class StageReviewServiceImpl implements StageReviewService{

	@Autowired
	private StageReviewDao dao;
	
	@Override
	public List<StageReview> listComment(int stageNo) {
		System.out.println("호잇");
		return dao.listComment(stageNo);
	}

	@Override
	public void insertStageComment(StageReview sComment) {
		System.out.println("서비스까지 오냐");
		dao.insertStageComment(sComment);
	}

	@Override
	public void updateStageComment(StageReview sComment2) {
		System.out.println("가기 전 "+sComment2);
		dao.updateStageComment(sComment2);
		System.out.println(sComment2);
	}

	@Override
	public void deleteStageCommentByStageCommentUserId(String stageCommentUserId) {
		dao.deleteStageCommentByStageCommentUserId(stageCommentUserId);
	}

}

package com.buskstop.service;

import java.util.List;

import com.buskstop.vo.Stage;
import com.buskstop.vo.StageReview;

public interface StageReviewService {

	List<StageReview> listComment(int stageNo);

	void insertStageComment(StageReview sComment);

	void updateStageComment(StageReview sComment2);

	void deleteStageCommentByStageCommentUserId(String stageCommentUserId);

}

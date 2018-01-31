package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.Stage;
import com.buskstop.vo.StageReview;

public interface StageReviewDao {

	List<StageReview> listComment(int stageNo);

	int insertStageComment(StageReview sComment);

	int updateStageComment(StageReview sComment2);

	int deleteStageCommentByStageCommentUserId(String stageCommentUserId);

}

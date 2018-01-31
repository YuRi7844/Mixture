package com.buskstop.vo;

import java.io.Serializable;

import com.buskstop.common.util.DateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class StageReview implements Serializable{
	private int stageNo;
	private int starScore;
	private String stageCommentUserId;
	private String stageComment;
//	@JsonSerialize(using=DateJsonSerializer.class)
	private String stageCommentRegTime;
	
	public StageReview() {
	}
	
	public StageReview(int stageNo, int starScore, String stageCommentUserId, String stageComment,
			String stageCommentRegTime) {
		this.stageNo = stageNo;
		this.starScore = starScore;
		this.stageCommentUserId = stageCommentUserId;
		this.stageComment = stageComment;
		this.stageCommentRegTime = stageCommentRegTime;
	}
	public StageReview(int stageNo, String stageComment) {
		this.stageNo = stageNo;
		this.stageComment = stageComment;
	}
	public StageReview(int stageNo, int starScore, String stageComment) {
		this.stageNo = stageNo;
		this.starScore = starScore;
		this.stageComment = stageComment;
	}

	@Override
	public String toString() {
		return "StageReview [stageNo=" + stageNo + ", starScore=" + starScore + ", stageCommentUserId="
				+ stageCommentUserId + ", stageComment=" + stageComment + ", stageCommentRegTime=" + stageCommentRegTime
				+ "]";
	}




	public int getStageNo() {
		return stageNo;
	}

	public int getStarScore() {
		return starScore;
	}

	public String getStageCommentUserId() {
		return stageCommentUserId;
	}

	public String getStageComment() {
		return stageComment;
	}

	public String getStageCommentRegTime() {
		return stageCommentRegTime;
	}

	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}

	public void setStarScore(int starScore) {
		this.starScore = starScore;
	}

	public void setStageCommentUserId(String stageCommentUserId) {
		this.stageCommentUserId = stageCommentUserId;
	}

	public void setStageComment(String stageComment) {
		this.stageComment = stageComment;
	}

	public void setStageCommentRegTime(String stageCommentRegTime) {
		this.stageCommentRegTime = stageCommentRegTime;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stageComment == null) ? 0 : stageComment.hashCode());
		result = prime * result + ((stageCommentRegTime == null) ? 0 : stageCommentRegTime.hashCode());
		result = prime * result + ((stageCommentUserId == null) ? 0 : stageCommentUserId.hashCode());
		result = prime * result + stageNo;
		result = prime * result + starScore;
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StageReview other = (StageReview) obj;
		if (stageComment == null) {
			if (other.stageComment != null)
				return false;
		} else if (!stageComment.equals(other.stageComment))
			return false;
		if (stageCommentRegTime == null) {
			if (other.stageCommentRegTime != null)
				return false;
		} else if (!stageCommentRegTime.equals(other.stageCommentRegTime))
			return false;
		if (stageCommentUserId == null) {
			if (other.stageCommentUserId != null)
				return false;
		} else if (!stageCommentUserId.equals(other.stageCommentUserId))
			return false;
		if (stageNo != other.stageNo)
			return false;
		if (starScore != other.starScore)
			return false;
		return true;
	}

	
	
}

package com.buskstop.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.buskstop.common.util.DateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class VideoComment implements Serializable{
	
	private int videoNo;
	private int videoCommentNo;
	private String videoCommentUserId;
	private String videoComment;
	
	@JsonSerialize(using=DateJsonSerializer.class)
	private Date videoCommentRegTime;
	
	public VideoComment() {
	}

	public VideoComment(int videoNo, String videoComment) {
		this.videoNo = videoNo;
		this.videoComment = videoComment;
	}


	public VideoComment(int videoNo, int videoCommentNo, String videoCommentUserId, String videoComment,
			Date videoCommentRegTime) {
		super();
		this.videoNo = videoNo;
		this.videoCommentNo = videoCommentNo;
		this.videoCommentUserId = videoCommentUserId;
		this.videoComment = videoComment;
		this.videoCommentRegTime = videoCommentRegTime;
	}

	public int getVideoNo() {
		return videoNo;
	}

	public void setVideoNo(int videoNo) {
		this.videoNo = videoNo;
	}

	public int getVideoCommentNo() {
		return videoCommentNo;
	}

	public void setVideoCommentNo(int videoCommentNo) {
		this.videoCommentNo = videoCommentNo;
	}

	public String getVideoCommentUserId() {
		return videoCommentUserId;
	}

	public void setVideoCommentUserId(String videoCommentUserId) {
		this.videoCommentUserId = videoCommentUserId;
	}

	public String getVideoComment() {
		return videoComment;
	}

	public void setVideoComment(String videoComment) {
		this.videoComment = videoComment;
	}
	
	public Date getVideoCommentRegTime() {
		return videoCommentRegTime;
	}

	public void setVideoCommentRegTime(Date videoCommentRegTime) {
		this.videoCommentRegTime = videoCommentRegTime;
	}

	@Override
	public String toString() {
		return "VideoComment [videoNo=" + videoNo + ", videoCommentNo=" + videoCommentNo + ", videoCommentUserId="
				+ videoCommentUserId + ", videoComment=" + videoComment + ", videoCommentRegTime=" + videoCommentRegTime
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((videoComment == null) ? 0 : videoComment.hashCode());
		result = prime * result + videoCommentNo;
		result = prime * result + ((videoCommentRegTime == null) ? 0 : videoCommentRegTime.hashCode());
		result = prime * result + ((videoCommentUserId == null) ? 0 : videoCommentUserId.hashCode());
		result = prime * result + videoNo;
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
		VideoComment other = (VideoComment) obj;
		if (videoComment == null) {
			if (other.videoComment != null)
				return false;
		} else if (!videoComment.equals(other.videoComment))
			return false;
		if (videoCommentNo != other.videoCommentNo)
			return false;
		if (videoCommentRegTime == null) {
			if (other.videoCommentRegTime != null)
				return false;
		} else if (!videoCommentRegTime.equals(other.videoCommentRegTime))
			return false;
		if (videoCommentUserId == null) {
			if (other.videoCommentUserId != null)
				return false;
		} else if (!videoCommentUserId.equals(other.videoCommentUserId))
			return false;
		if (videoNo != other.videoNo)
			return false;
		return true;
	}
	
}

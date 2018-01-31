package com.buskstop.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Video implements Serializable{
	private int videoNo;
	private String videoTitle; 
	private String videoLink; 
	private String videoLocation; //영상속 장소
	private String videoContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date videoDate; 
	private String videoArtist;
	private String videoCategory;//아티스트 홍보영상, 공연영상, 연습영상
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date videoRegTime;

	private String videoUserId;
	private int videoHits;//조회수
	
	private int likeCount;
	
	private List<VideoComment> commentList;
	
	public Video() {
	}

	
	public Video(int videoNo, int likeCount) {
		this.videoNo = videoNo;
		this.likeCount = likeCount;
	}


	public Video(int videoNo, String videoTitle, String videoLink, String videoLocation, String videoContent,
			Date videoDate) {
		this.videoNo = videoNo;
		this.videoTitle = videoTitle;
		this.videoLink = videoLink;
		this.videoLocation = videoLocation;
		this.videoContent = videoContent;
		this.videoDate = videoDate;
	}
	
	
	
	public Video(int videoNo, String videoTitle, String videoLink, String videoLocation, String videoContent,
			Date videoDate, String videoArtist, String videoCategory) {
		this.videoNo = videoNo;
		this.videoTitle = videoTitle;
		this.videoLink = videoLink;
		this.videoLocation = videoLocation;
		this.videoContent = videoContent;
		this.videoDate = videoDate;
		this.videoArtist = videoArtist;
		this.videoCategory = videoCategory;
	}


	public Video(int videoNo, String videoTitle, String videoLink, String videoLocation, String videoContent,
			Date videoDate, String videoArtist, String videoCategory, Date videoRegTime, String videoUserId,
			int videoHits) {
		this.videoNo = videoNo;
		this.videoTitle = videoTitle;
		this.videoLink = videoLink;
		this.videoLocation = videoLocation;
		this.videoContent = videoContent;
		this.videoDate = videoDate;
		this.videoArtist = videoArtist;
		this.videoCategory = videoCategory;
		this.videoRegTime = videoRegTime;
		this.videoUserId = videoUserId;
		this.videoHits = videoHits;
	}
	
	
	
	public Video(int videoNo, String videoTitle, String videoLink, String videoLocation, String videoContent,
			Date videoDate, String videoArtist, String videoCategory, Date videoRegTime, String videoUserId,
			int videoHits, List<VideoComment> commentList) {
		this.videoNo = videoNo;
		this.videoTitle = videoTitle;
		this.videoLink = videoLink;
		this.videoLocation = videoLocation;
		this.videoContent = videoContent;
		this.videoDate = videoDate;
		this.videoArtist = videoArtist;
		this.videoCategory = videoCategory;
		this.videoRegTime = videoRegTime;
		this.videoUserId = videoUserId;
		this.videoHits = videoHits;
		this.commentList = commentList;
	}

	
	
	public int getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public List<VideoComment> getCommentList() {
		return commentList;
	}


	public void setCommentList(List<VideoComment> commentList) {
		this.commentList = commentList;
	}


	public int getVideoNo() {
		return videoNo;
	}


	public void setVideoNo(int videoNo) {
		this.videoNo = videoNo;
	}


	public String getVideoTitle() {
		return videoTitle;
	}


	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}


	public String getVideoLink() {
		return videoLink;
	}


	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}


	public String getVideoLocation() {
		return videoLocation;
	}


	public void setVideoLocation(String videoLocation) {
		this.videoLocation = videoLocation;
	}


	public String getVideoContent() {
		return videoContent;
	}


	public void setVideoContent(String videoContent) {
		this.videoContent = videoContent;
	}


	public Date getVideoDate() {
		return videoDate;
	}


	public void setVideoDate(Date videoDate) {
		this.videoDate = videoDate;
	}


	public String getVideoArtist() {
		return videoArtist;
	}


	public void setVideoArtist(String videoArtist) {
		this.videoArtist = videoArtist;
	}


	public String getVideoCategory() {
		return videoCategory;
	}


	public void setVideoCategory(String videoCategory) {
		this.videoCategory = videoCategory;
	}


	public Date getVideoRegTime() {
		return videoRegTime;
	}


	public void setVideoRegTime(Date videoRegTime) {
		this.videoRegTime = videoRegTime;
	}


	public String getVideoUserId() {
		return videoUserId;
	}


	public void setVideoUserId(String videoUserId) {
		this.videoUserId = videoUserId;
	}


	public int getVideoHits() {
		return videoHits;
	}


	public void setVideoHits(int videoHits) {
		this.videoHits = videoHits;
	}

	
	@Override
	public String toString() {
		return "Video [videoNo=" + videoNo + ", videoTitle=" + videoTitle + ", videoLink=" + videoLink
				+ ", videoLocation=" + videoLocation + ", videoContent=" + videoContent + ", videoDate=" + videoDate
				+ ", videoArtist=" + videoArtist + ", videoCategory=" + videoCategory + ", videoRegTime=" + videoRegTime
				+ ", videoUserId=" + videoUserId + ", videoHits=" + videoHits + ", likeCount=" + likeCount
				+ ", commentList=" + commentList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((videoArtist == null) ? 0 : videoArtist.hashCode());
		result = prime * result + ((videoCategory == null) ? 0 : videoCategory.hashCode());
		result = prime * result + ((videoContent == null) ? 0 : videoContent.hashCode());
		result = prime * result + ((videoDate == null) ? 0 : videoDate.hashCode());
		result = prime * result + videoHits;
		result = prime * result + ((videoLink == null) ? 0 : videoLink.hashCode());
		result = prime * result + ((videoLocation == null) ? 0 : videoLocation.hashCode());
		result = prime * result + videoNo;
		result = prime * result + ((videoRegTime == null) ? 0 : videoRegTime.hashCode());
		result = prime * result + ((videoTitle == null) ? 0 : videoTitle.hashCode());
		result = prime * result + ((videoUserId == null) ? 0 : videoUserId.hashCode());
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
		Video other = (Video) obj;
		if (videoArtist == null) {
			if (other.videoArtist != null)
				return false;
		} else if (!videoArtist.equals(other.videoArtist))
			return false;
		if (videoCategory == null) {
			if (other.videoCategory != null)
				return false;
		} else if (!videoCategory.equals(other.videoCategory))
			return false;
		if (videoContent == null) {
			if (other.videoContent != null)
				return false;
		} else if (!videoContent.equals(other.videoContent))
			return false;
		if (videoDate == null) {
			if (other.videoDate != null)
				return false;
		} else if (!videoDate.equals(other.videoDate))
			return false;
		if (videoHits != other.videoHits)
			return false;
		if (videoLink == null) {
			if (other.videoLink != null)
				return false;
		} else if (!videoLink.equals(other.videoLink))
			return false;
		if (videoLocation == null) {
			if (other.videoLocation != null)
				return false;
		} else if (!videoLocation.equals(other.videoLocation))
			return false;
		if (videoNo != other.videoNo)
			return false;
		if (videoRegTime == null) {
			if (other.videoRegTime != null)
				return false;
		} else if (!videoRegTime.equals(other.videoRegTime))
			return false;
		if (videoTitle == null) {
			if (other.videoTitle != null)
				return false;
		} else if (!videoTitle.equals(other.videoTitle))
			return false;
		if (videoUserId == null) {
			if (other.videoUserId != null)
				return false;
		} else if (!videoUserId.equals(other.videoUserId))
			return false;
		return true;
	}
}
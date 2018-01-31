package com.buskstop.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class HelpComment {

	private int helpCommentNo;
	private int helpNo;
	private String helpComment;
	private String helpCommentId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date helpCommentRegTime;
	
	public HelpComment() {}

	public HelpComment(int helpCommentNo, int helpNo, String helpComment, String helpCommentId,
			Date helpCommentRegTime) {
		this.helpCommentNo = helpCommentNo;
		this.helpNo = helpNo;
		this.helpComment = helpComment;
		this.helpCommentId = helpCommentId;
		this.helpCommentRegTime = helpCommentRegTime;
	}

	public int getHelpCommentNo() {
		return helpCommentNo;
	}

	public void setHelpCommentNo(int helpCommentNo) {
		this.helpCommentNo = helpCommentNo;
	}

	public int getHelpNo() {
		return helpNo;
	}

	public void setHelpNo(int helpNo) {
		this.helpNo = helpNo;
	}

	public String getHelpComment() {
		return helpComment;
	}

	public void setHelpComment(String helpComment) {
		this.helpComment = helpComment;
	}

	public String getHelpCommentId() {
		return helpCommentId;
	}

	public void setHelpCommentId(String helpCommentId) {
		this.helpCommentId = helpCommentId;
	}

	public Date getHelpCommentRegTime() {
		return helpCommentRegTime;
	}

	public void setHelpCommentRegTime(Date helpCommentRegTime) {
		this.helpCommentRegTime = helpCommentRegTime;
	}

	@Override
	public String toString() {
		return "HelpComment [helpCommentNo=" + helpCommentNo + ", helpNo=" + helpNo + ", helpComment=" + helpComment
				+ ", helpCommentId=" + helpCommentId + ", helpCommentRegTime=" + helpCommentRegTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((helpComment == null) ? 0 : helpComment.hashCode());
		result = prime * result + ((helpCommentId == null) ? 0 : helpCommentId.hashCode());
		result = prime * result + helpCommentNo;
		result = prime * result + ((helpCommentRegTime == null) ? 0 : helpCommentRegTime.hashCode());
		result = prime * result + helpNo;
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
		HelpComment other = (HelpComment) obj;
		if (helpComment == null) {
			if (other.helpComment != null)
				return false;
		} else if (!helpComment.equals(other.helpComment))
			return false;
		if (helpCommentId == null) {
			if (other.helpCommentId != null)
				return false;
		} else if (!helpCommentId.equals(other.helpCommentId))
			return false;
		if (helpCommentNo != other.helpCommentNo)
			return false;
		if (helpCommentRegTime == null) {
			if (other.helpCommentRegTime != null)
				return false;
		} else if (!helpCommentRegTime.equals(other.helpCommentRegTime))
			return false;
		if (helpNo != other.helpNo)
			return false;
		return true;
	}
	
}

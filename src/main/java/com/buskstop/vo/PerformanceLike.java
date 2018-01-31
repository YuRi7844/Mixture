package com.buskstop.vo;

import java.io.Serializable;
import java.util.List;

public class PerformanceLike implements Serializable {
	private int performanceLikeNo;
	private String performanceLikeUserId;
	private List<User> likeUser;
	
	public PerformanceLike() {}

	public PerformanceLike(int performanceLikeNo, String performanceLikeUserId) {
		this.performanceLikeNo = performanceLikeNo;
		this.performanceLikeUserId = performanceLikeUserId;
	}

	public int getPerformanceLikeNo() {
		return performanceLikeNo;
	}

	public void setPerformanceLikeNo(int performanceLikeNo) {
		this.performanceLikeNo = performanceLikeNo;
	}

	public String getPerformanceLikeUserId() {
		return performanceLikeUserId;
	}

	public void setPerformanceLikeUserId(String performanceLikeUserId) {
		this.performanceLikeUserId = performanceLikeUserId;
	}

	public List<User> getLikeUser() {
		return likeUser;
	}

	public void setLikeUser(List<User> likeUser) {
		this.likeUser = likeUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((likeUser == null) ? 0 : likeUser.hashCode());
		result = prime * result + performanceLikeNo;
		result = prime * result + ((performanceLikeUserId == null) ? 0 : performanceLikeUserId.hashCode());
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
		PerformanceLike other = (PerformanceLike) obj;
		if (likeUser == null) {
			if (other.likeUser != null)
				return false;
		} else if (!likeUser.equals(other.likeUser))
			return false;
		if (performanceLikeNo != other.performanceLikeNo)
			return false;
		if (performanceLikeUserId == null) {
			if (other.performanceLikeUserId != null)
				return false;
		} else if (!performanceLikeUserId.equals(other.performanceLikeUserId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PerformanceLike [performanceLikeNo=" + performanceLikeNo + ", performanceLikeUserId="
				+ performanceLikeUserId + ", likeUser=" + likeUser + "]";
	}
	
	
}

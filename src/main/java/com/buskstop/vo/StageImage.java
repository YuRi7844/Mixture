package com.buskstop.vo;

import java.io.Serializable;

public class StageImage implements Serializable{
	private int stageImageNo;
	private String stageImageLocation;
	private int stageNo;
	
	public StageImage() {
	}
	
	public StageImage(int stageImageNo, String stageImageLocation, int stageNo) {
		this.stageImageNo = stageImageNo;
		this.stageImageLocation = stageImageLocation;
		this.stageNo = stageNo;
	}

	public int getStageImageNo() {
		return stageImageNo;
	}

	public void setStageImageNo(int stageImageNo) {
		this.stageImageNo = stageImageNo;
	}

	public String getStageImageLocation() {
		return stageImageLocation;
	}

	public void setStageImageLocation(String stageImageLocation) {
		this.stageImageLocation = stageImageLocation;
	}

	public int getStageNo() {
		return stageNo;
	}

	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}

	@Override
	public String toString() {
		return "StageImage [stageImageNo=" + stageImageNo + ", stageImageLocation=" + stageImageLocation + ", stageNo="
				+ stageNo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stageImageLocation == null) ? 0 : stageImageLocation.hashCode());
		result = prime * result + stageImageNo;
		result = prime * result + stageNo;
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
		StageImage other = (StageImage) obj;
		if (stageImageLocation == null) {
			if (other.stageImageLocation != null)
				return false;
		} else if (!stageImageLocation.equals(other.stageImageLocation))
			return false;
		if (stageImageNo != other.stageImageNo)
			return false;
		if (stageNo != other.stageNo)
			return false;
		return true;
	}
	
}

package com.buskstop.vo;

import java.io.Serializable;
import java.util.List;

public class PremiumStageImage implements Serializable{
	private int stageImageNum;
	private String stageImageLocation;
	private int establishNum;
	
	private List<String> imageList;
	
	public PremiumStageImage() {
	}

	public PremiumStageImage(int stageImageNum, String stageImageLocation, int establishNum) {
		this.stageImageNum = stageImageNum;
		this.stageImageLocation = stageImageLocation;
		this.establishNum = establishNum;
	}

	
	
	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public int getStageImageNum() {
		return stageImageNum;
	}

	public void setStageImageNum(int stageImageNum) {
		this.stageImageNum = stageImageNum;
	}

	public String getStageImageLocation() {
		return stageImageLocation;
	}

	public void setStageImageLocation(String stageImageLocation) {
		this.stageImageLocation = stageImageLocation;
	}

	public int getEstablishNum() {
		return establishNum;
	}

	public void setEstablishNum(int establishNum) {
		this.establishNum = establishNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + establishNum;
		result = prime * result + ((stageImageLocation == null) ? 0 : stageImageLocation.hashCode());
		result = prime * result + stageImageNum;
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
		PremiumStageImage other = (PremiumStageImage) obj;
		if (establishNum != other.establishNum)
			return false;
		if (stageImageLocation == null) {
			if (other.stageImageLocation != null)
				return false;
		} else if (!stageImageLocation.equals(other.stageImageLocation))
			return false;
		if (stageImageNum != other.stageImageNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PremiumStageImage [stageImageNum=" + stageImageNum + ", stageImageLocation=" + stageImageLocation
				+ ", establishNum=" + establishNum + "]";
	}
	
	
}

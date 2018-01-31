package com.buskstop.vo;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Stage implements Serializable{
	private int stageNo;
	private String stageName;
	private String stageLocation;
	private int stageCost;
	private int stageArea;
	private String stageInstrument;
	private String stageContent;
	private int stageParking;
	private int stageDrinking;
	private int stageFoodSell;
	private int stageFoodRestriction;
	private int stageReservation;
	private String stageSellerId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date stageRegTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stageRentalDate;
	private List<StageImage> stageImage;
	private Date stageStartTime;
	private Date stageEndTime;
	
	
	private List<StageReview> reviewList;
	private int starpointAvg;
	
	public Stage() {
	}


	public Stage(int stageNo, String stageName, String stageLocation, int stageCost, int stageArea,
			String stageInstrument, String stageContent, int stageParking, int stageDrinking, int stageFoodSell,
			int stageFoodRestriction, int stageReservation, String stageSellerId, Date stageRegTime,
			Date stageRentalDate, List<StageImage> stageImage, Time stageStartTime, Time stageEndTime) {
		this.stageNo = stageNo;
		this.stageName = stageName;
		this.stageLocation = stageLocation;
		this.stageCost = stageCost;
		this.stageArea = stageArea;
		this.stageInstrument = stageInstrument;
		this.stageContent = stageContent;
		this.stageParking = stageParking;
		this.stageDrinking = stageDrinking;
		this.stageFoodSell = stageFoodSell;
		this.stageFoodRestriction = stageFoodRestriction;
		this.stageReservation = stageReservation;
		this.stageSellerId = stageSellerId;
		this.stageRegTime = stageRegTime;
		this.stageRentalDate = stageRentalDate;
		this.stageImage = stageImage;
		this.stageStartTime = stageStartTime;
		this.stageEndTime = stageEndTime;
	}

	

	public Stage(int stageNo, String stageName, String stageLocation, int stageCost, int stageArea,
			String stageInstrument, String stageContent, int stageParking, int stageDrinking, int stageFoodSell,
			int stageFoodRestriction, int stageReservation, String stageSellerId, Date stageRegTime,
			Date stageRentalDate, List<StageImage> stageImage, Date stageStartTime, Date stageEndTime,
			float averageStarPoint) {
		this.stageNo = stageNo;
		this.stageName = stageName;
		this.stageLocation = stageLocation;
		this.stageCost = stageCost;
		this.stageArea = stageArea;
		this.stageInstrument = stageInstrument;
		this.stageContent = stageContent;
		this.stageParking = stageParking;
		this.stageDrinking = stageDrinking;
		this.stageFoodSell = stageFoodSell;
		this.stageFoodRestriction = stageFoodRestriction;
		this.stageReservation = stageReservation;
		this.stageSellerId = stageSellerId;
		this.stageRegTime = stageRegTime;
		this.stageRentalDate = stageRentalDate;
		this.stageImage = stageImage;
		this.stageStartTime = stageStartTime;
		this.stageEndTime = stageEndTime;
	}

	
	
	public Stage(int stageNo, String stageName, String stageLocation, int stageCost, int stageArea,
			String stageInstrument, String stageContent, int stageParking, int stageDrinking, int stageFoodSell,
			int stageFoodRestriction, int stageReservation, String stageSellerId, Date stageRegTime,
			Date stageRentalDate, List<StageImage> stageImage, Date stageStartTime, Date stageEndTime,
			List<StageReview> reviewList) {
		this.stageNo = stageNo;
		this.stageName = stageName;
		this.stageLocation = stageLocation;
		this.stageCost = stageCost;
		this.stageArea = stageArea;
		this.stageInstrument = stageInstrument;
		this.stageContent = stageContent;
		this.stageParking = stageParking;
		this.stageDrinking = stageDrinking;
		this.stageFoodSell = stageFoodSell;
		this.stageFoodRestriction = stageFoodRestriction;
		this.stageReservation = stageReservation;
		this.stageSellerId = stageSellerId;
		this.stageRegTime = stageRegTime;
		this.stageRentalDate = stageRentalDate;
		this.stageImage = stageImage;
		this.stageStartTime = stageStartTime;
		this.stageEndTime = stageEndTime;
		this.reviewList = reviewList;
	}

	
	
	public Stage(int stageNo, String stageName, String stageLocation, int stageCost, int stageArea,
			String stageInstrument, String stageContent, int stageParking, int stageDrinking, int stageFoodSell,
			int stageFoodRestriction, int stageReservation, String stageSellerId, Date stageRegTime,
			Date stageRentalDate, List<StageImage> stageImage, Date stageStartTime, Date stageEndTime,
			List<StageReview> reviewList, int starpointAvg) {
		this.stageNo = stageNo;
		this.stageName = stageName;
		this.stageLocation = stageLocation;
		this.stageCost = stageCost;
		this.stageArea = stageArea;
		this.stageInstrument = stageInstrument;
		this.stageContent = stageContent;
		this.stageParking = stageParking;
		this.stageDrinking = stageDrinking;
		this.stageFoodSell = stageFoodSell;
		this.stageFoodRestriction = stageFoodRestriction;
		this.stageReservation = stageReservation;
		this.stageSellerId = stageSellerId;
		this.stageRegTime = stageRegTime;
		this.stageRentalDate = stageRentalDate;
		this.stageImage = stageImage;
		this.stageStartTime = stageStartTime;
		this.stageEndTime = stageEndTime;
		this.reviewList = reviewList;
		this.starpointAvg = starpointAvg;
	}


	@Override
	public String toString() {
		return "Stage [stageNo=" + stageNo + ", stageName=" + stageName + ", stageLocation=" + stageLocation
				+ ", stageCost=" + stageCost + ", stageArea=" + stageArea + ", stageInstrument=" + stageInstrument
				+ ", stageContent=" + stageContent + ", stageParking=" + stageParking + ", stageDrinking="
				+ stageDrinking + ", stageFoodSell=" + stageFoodSell + ", stageFoodRestriction=" + stageFoodRestriction
				+ ", stageReservation=" + stageReservation + ", stageSellerId=" + stageSellerId + ", stageRegTime="
				+ stageRegTime + ", stageRentalDate=" + stageRentalDate + ", stageImage=" + stageImage
				+ ", stageStartTime=" + stageStartTime + ", stageEndTime=" + stageEndTime + "]";
	}

	
	
	
	public int getStarpointAvg() {
		return starpointAvg;
	}


	public void setStarpointAvg(int starpointAvg) {
		this.starpointAvg = starpointAvg;
	}


	public List<StageReview> getReviewList() {
		return reviewList;
	}


	public void setReviewList(List<StageReview> reviewList) {
		this.reviewList = reviewList;
	}


	public int getStageNo() {
		return stageNo;
	}


	public String getStageName() {
		return stageName;
	}


	public String getStageLocation() {
		return stageLocation;
	}


	public int getStageCost() {
		return stageCost;
	}


	public int getStageArea() {
		return stageArea;
	}


	public String getStageInstrument() {
		return stageInstrument;
	}


	public String getStageContent() {
		return stageContent;
	}


	public int getStageParking() {
		return stageParking;
	}


	public int getStageDrinking() {
		return stageDrinking;
	}


	public int getStageFoodSell() {
		return stageFoodSell;
	}


	public int getStageFoodRestriction() {
		return stageFoodRestriction;
	}


	public int getStageReservation() {
		return stageReservation;
	}


	public String getStageSellerId() {
		return stageSellerId;
	}


	public Date getStageRegTime() {
		return stageRegTime;
	}


	public Date getStageRentalDate() {
		return stageRentalDate;
	}


	public List<StageImage> getStageImage() {
		return stageImage;
	}


	public Date getStageStartTime() {
		return stageStartTime;
	}


	public Date getStageEndTime() {
		return stageEndTime;
	}


	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}


	public void setStageName(String stageName) {
		this.stageName = stageName;
	}


	public void setStageLocation(String stageLocation) {
		this.stageLocation = stageLocation;
	}


	public void setStageCost(int stageCost) {
		this.stageCost = stageCost;
	}


	public void setStageArea(int stageArea) {
		this.stageArea = stageArea;
	}


	public void setStageInstrument(String stageInstrument) {
		this.stageInstrument = stageInstrument;
	}


	public void setStageContent(String stageContent) {
		this.stageContent = stageContent;
	}


	public void setStageParking(int stageParking) {
		this.stageParking = stageParking;
	}


	public void setStageDrinking(int stageDrinking) {
		this.stageDrinking = stageDrinking;
	}


	public void setStageFoodSell(int stageFoodSell) {
		this.stageFoodSell = stageFoodSell;
	}


	public void setStageFoodRestriction(int stageFoodRestriction) {
		this.stageFoodRestriction = stageFoodRestriction;
	}


	public void setStageReservation(int stageReservation) {
		this.stageReservation = stageReservation;
	}


	public void setStageSellerId(String stageSellerId) {
		this.stageSellerId = stageSellerId;
	}


	public void setStageRegTime(Date stageRegTime) {
		this.stageRegTime = stageRegTime;
	}


	public void setStageRentalDate(Date stageRentalDate) {
		this.stageRentalDate = stageRentalDate;
	}


	public void setStageImage(List<StageImage> stageImage) {
		this.stageImage = stageImage;
	}


	@DateTimeFormat(pattern = "HH:mm")
	public void setStageStartTime(Date stageStartTime) {
		this.stageStartTime = new Time(stageStartTime.getTime());
	}


	@DateTimeFormat(pattern = "HH:mm")
	public void setStageEndTime(Date stageEndTime) {
		this.stageEndTime = new Time(stageEndTime.getTime());
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stageArea;
		result = prime * result + ((stageContent == null) ? 0 : stageContent.hashCode());
		result = prime * result + stageCost;
		result = prime * result + stageDrinking;
		result = prime * result + ((stageEndTime == null) ? 0 : stageEndTime.hashCode());
		result = prime * result + stageFoodRestriction;
		result = prime * result + stageFoodSell;
		result = prime * result + ((stageImage == null) ? 0 : stageImage.hashCode());
		result = prime * result + ((stageInstrument == null) ? 0 : stageInstrument.hashCode());
		result = prime * result + ((stageLocation == null) ? 0 : stageLocation.hashCode());
		result = prime * result + ((stageName == null) ? 0 : stageName.hashCode());
		result = prime * result + stageNo;
		result = prime * result + stageParking;
		result = prime * result + ((stageRegTime == null) ? 0 : stageRegTime.hashCode());
		result = prime * result + ((stageRentalDate == null) ? 0 : stageRentalDate.hashCode());
		result = prime * result + stageReservation;
		result = prime * result + ((stageSellerId == null) ? 0 : stageSellerId.hashCode());
		result = prime * result + ((stageStartTime == null) ? 0 : stageStartTime.hashCode());
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
		Stage other = (Stage) obj;
		if (stageArea != other.stageArea)
			return false;
		if (stageContent == null) {
			if (other.stageContent != null)
				return false;
		} else if (!stageContent.equals(other.stageContent))
			return false;
		if (stageCost != other.stageCost)
			return false;
		if (stageDrinking != other.stageDrinking)
			return false;
		if (stageEndTime == null) {
			if (other.stageEndTime != null)
				return false;
		} else if (!stageEndTime.equals(other.stageEndTime))
			return false;
		if (stageFoodRestriction != other.stageFoodRestriction)
			return false;
		if (stageFoodSell != other.stageFoodSell)
			return false;
		if (stageImage == null) {
			if (other.stageImage != null)
				return false;
		} else if (!stageImage.equals(other.stageImage))
			return false;
		if (stageInstrument == null) {
			if (other.stageInstrument != null)
				return false;
		} else if (!stageInstrument.equals(other.stageInstrument))
			return false;
		if (stageLocation == null) {
			if (other.stageLocation != null)
				return false;
		} else if (!stageLocation.equals(other.stageLocation))
			return false;
		if (stageName == null) {
			if (other.stageName != null)
				return false;
		} else if (!stageName.equals(other.stageName))
			return false;
		if (stageNo != other.stageNo)
			return false;
		if (stageParking != other.stageParking)
			return false;
		if (stageRegTime == null) {
			if (other.stageRegTime != null)
				return false;
		} else if (!stageRegTime.equals(other.stageRegTime))
			return false;
		if (stageRentalDate == null) {
			if (other.stageRentalDate != null)
				return false;
		} else if (!stageRentalDate.equals(other.stageRentalDate))
			return false;
		if (stageReservation != other.stageReservation)
			return false;
		if (stageSellerId == null) {
			if (other.stageSellerId != null)
				return false;
		} else if (!stageSellerId.equals(other.stageSellerId))
			return false;
		if (stageStartTime == null) {
			if (other.stageStartTime != null)
				return false;
		} else if (!stageStartTime.equals(other.stageStartTime))
			return false;
		return true;
	}



	
}

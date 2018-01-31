package com.buskstop.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PremiumStage implements Serializable {

	private int establishNum; // 사업장번호
	private String operatorUserId;
	private int operatorNo;
	private String stageName; // 장소명
	private String stageLocation; // 주소
	private int stageArea; // 면적
	private String stageInstrument;
	private String stageContent;
	private int stageParking;
	private int stageDrinking;
	private int stageFoodSell;
	private int stageFoodRestriction;
	private List<MultipartFile> multiImage;
	private String stageImage; // 대표사진
	
	private List<String> oldImage;

	public PremiumStage() {
	}

	public PremiumStage(int establishNum, String operatorUserId, int operatorNo, String stageName, String stageLocation,
			int stageArea, String stageInstrument, String stageContent, int stageParking, int stageDrinking,
			int stageFoodSell, int stageFoodRestriction, List<MultipartFile> multiImage, String stageImage,
			List<String> oldImage) {
		super();
		this.establishNum = establishNum;
		this.operatorUserId = operatorUserId;
		this.operatorNo = operatorNo;
		this.stageName = stageName;
		this.stageLocation = stageLocation;
		this.stageArea = stageArea;
		this.stageInstrument = stageInstrument;
		this.stageContent = stageContent;
		this.stageParking = stageParking;
		this.stageDrinking = stageDrinking;
		this.stageFoodSell = stageFoodSell;
		this.stageFoodRestriction = stageFoodRestriction;
		this.multiImage = multiImage;
		this.stageImage = stageImage;
		this.oldImage = oldImage;
	}

	public int getEstablishNum() {
		return establishNum;
	}

	public void setEstablishNum(int establishNum) {
		this.establishNum = establishNum;
	}

	public String getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(String operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	public int getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(int operatorNo) {
		this.operatorNo = operatorNo;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getStageLocation() {
		return stageLocation;
	}

	public void setStageLocation(String stageLocation) {
		this.stageLocation = stageLocation;
	}

	public int getStageArea() {
		return stageArea;
	}

	public void setStageArea(int stageArea) {
		this.stageArea = stageArea;
	}

	public String getStageInstrument() {
		return stageInstrument;
	}

	public void setStageInstrument(String stageInstrument) {
		this.stageInstrument = stageInstrument;
	}

	public String getStageContent() {
		return stageContent;
	}

	public void setStageContent(String stageContent) {
		this.stageContent = stageContent;
	}

	public int getStageParking() {
		return stageParking;
	}

	public void setStageParking(int stageParking) {
		this.stageParking = stageParking;
	}

	public int getStageDrinking() {
		return stageDrinking;
	}

	public void setStageDrinking(int stageDrinking) {
		this.stageDrinking = stageDrinking;
	}

	public int getStageFoodSell() {
		return stageFoodSell;
	}

	public void setStageFoodSell(int stageFoodSell) {
		this.stageFoodSell = stageFoodSell;
	}

	public int getStageFoodRestriction() {
		return stageFoodRestriction;
	}

	public void setStageFoodRestriction(int stageFoodRestriction) {
		this.stageFoodRestriction = stageFoodRestriction;
	}

	public List<MultipartFile> getMultiImage() {
		return multiImage;
	}

	public void setMultiImage(List<MultipartFile> multiImage) {
		this.multiImage = multiImage;
	}

	public String getStageImage() {
		return stageImage;
	}

	public void setStageImage(String stageImage) {
		this.stageImage = stageImage;
	}

	public List<String> getOldImage() {
		return oldImage;
	}

	public void setOldImage(List<String> oldImage) {
		this.oldImage = oldImage;
	}

	@Override
	public String toString() {
		return "PremiumStage [establishNum=" + establishNum + ", operatorUserId=" + operatorUserId + ", operatorNo="
				+ operatorNo + ", stageName=" + stageName + ", stageLocation=" + stageLocation + ", stageArea="
				+ stageArea + ", stageInstrument=" + stageInstrument + ", stageContent=" + stageContent
				+ ", stageParking=" + stageParking + ", stageDrinking=" + stageDrinking + ", stageFoodSell="
				+ stageFoodSell + ", stageFoodRestriction=" + stageFoodRestriction + ", multiImage=" + multiImage
				+ ", stageImage=" + stageImage + ", oldImage=" + oldImage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + establishNum;
		result = prime * result + ((multiImage == null) ? 0 : multiImage.hashCode());
		result = prime * result + ((oldImage == null) ? 0 : oldImage.hashCode());
		result = prime * result + operatorNo;
		result = prime * result + ((operatorUserId == null) ? 0 : operatorUserId.hashCode());
		result = prime * result + stageArea;
		result = prime * result + ((stageContent == null) ? 0 : stageContent.hashCode());
		result = prime * result + stageDrinking;
		result = prime * result + stageFoodRestriction;
		result = prime * result + stageFoodSell;
		result = prime * result + ((stageImage == null) ? 0 : stageImage.hashCode());
		result = prime * result + ((stageInstrument == null) ? 0 : stageInstrument.hashCode());
		result = prime * result + ((stageLocation == null) ? 0 : stageLocation.hashCode());
		result = prime * result + ((stageName == null) ? 0 : stageName.hashCode());
		result = prime * result + stageParking;
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
		PremiumStage other = (PremiumStage) obj;
		if (establishNum != other.establishNum)
			return false;
		if (multiImage == null) {
			if (other.multiImage != null)
				return false;
		} else if (!multiImage.equals(other.multiImage))
			return false;
		if (oldImage == null) {
			if (other.oldImage != null)
				return false;
		} else if (!oldImage.equals(other.oldImage))
			return false;
		if (operatorNo != other.operatorNo)
			return false;
		if (operatorUserId == null) {
			if (other.operatorUserId != null)
				return false;
		} else if (!operatorUserId.equals(other.operatorUserId))
			return false;
		if (stageArea != other.stageArea)
			return false;
		if (stageContent == null) {
			if (other.stageContent != null)
				return false;
		} else if (!stageContent.equals(other.stageContent))
			return false;
		if (stageDrinking != other.stageDrinking)
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
		if (stageParking != other.stageParking)
			return false;
		return true;
	}
	
}

package com.buskstop.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StageReservation {
	
	private int rentalNoNumber;
	private String rentalUserId;
	private int stageNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date rentalDate;
	private int rentalStateCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date rentalRegTime;
	private String stageName;
	private String stageSellerId;
	
	public StageReservation() {}
	
	public StageReservation(int rentalNoNumber, String rentalUserId, int stageNo, Date rentalDate, int rentalStateCode,
			Date rentalRegTime) {
		this.rentalNoNumber = rentalNoNumber;
		this.rentalUserId = rentalUserId;
		this.stageNo = stageNo;
		this.rentalDate = rentalDate;
		this.rentalStateCode = rentalStateCode;
		this.rentalRegTime = rentalRegTime;
	}
	
	public int getRentalNoNumber() {
		return rentalNoNumber;
	}
	public void setRentalNoNumber(int rentalNoNumber) {
		this.rentalNoNumber = rentalNoNumber;
	}
	public String getRentalUserId() {
		return rentalUserId;
	}
	public void setRentalUserId(String rentalUserId) {
		this.rentalUserId = rentalUserId;
	}
	public int getStageNo() {
		return stageNo;
	}
	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}
	public Date getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	public int getRentalStateCode() {
		return rentalStateCode;
	}
	public void setRentalStateCode(int rentalStateCode) {
		this.rentalStateCode = rentalStateCode;
	}
	public Date getRentalRegTime() {
		return rentalRegTime;
	}
	public void setRentalRegTime(Date rentalRegTime) {
		this.rentalRegTime = rentalRegTime;
	}
	
	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getStageSellerId() {
		return stageSellerId;
	}

	public void setStageSellerId(String stageSellerId) {
		this.stageSellerId = stageSellerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rentalDate == null) ? 0 : rentalDate.hashCode());
		result = prime * result + rentalNoNumber;
		result = prime * result + ((rentalRegTime == null) ? 0 : rentalRegTime.hashCode());
		result = prime * result + rentalStateCode;
		result = prime * result + ((rentalUserId == null) ? 0 : rentalUserId.hashCode());
		result = prime * result + ((stageName == null) ? 0 : stageName.hashCode());
		result = prime * result + stageNo;
		result = prime * result + ((stageSellerId == null) ? 0 : stageSellerId.hashCode());
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
		StageReservation other = (StageReservation) obj;
		if (rentalDate == null) {
			if (other.rentalDate != null)
				return false;
		} else if (!rentalDate.equals(other.rentalDate))
			return false;
		if (rentalNoNumber != other.rentalNoNumber)
			return false;
		if (rentalRegTime == null) {
			if (other.rentalRegTime != null)
				return false;
		} else if (!rentalRegTime.equals(other.rentalRegTime))
			return false;
		if (rentalStateCode != other.rentalStateCode)
			return false;
		if (rentalUserId == null) {
			if (other.rentalUserId != null)
				return false;
		} else if (!rentalUserId.equals(other.rentalUserId))
			return false;
		if (stageName == null) {
			if (other.stageName != null)
				return false;
		} else if (!stageName.equals(other.stageName))
			return false;
		if (stageNo != other.stageNo)
			return false;
		if (stageSellerId == null) {
			if (other.stageSellerId != null)
				return false;
		} else if (!stageSellerId.equals(other.stageSellerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StageReservation [rentalNoNumber=" + rentalNoNumber + ", rentalUserId=" + rentalUserId + ", stageNo="
				+ stageNo + ", rentalDate=" + rentalDate + ", rentalStateCode=" + rentalStateCode + ", rentalRegTime="
				+ rentalRegTime + ", stageName=" + stageName + ", stageSellerId=" + stageSellerId + "]";
	}

}

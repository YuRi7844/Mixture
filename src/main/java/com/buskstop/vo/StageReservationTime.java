package com.buskstop.vo;

public class StageReservationTime {
	
	private int timeCode;
	private int stageNo;
	
	public StageReservationTime() {}

	public StageReservationTime(int timeCode, int stageNo) {
		this.timeCode = timeCode;
		this.stageNo = stageNo;
	}

	@Override
	public String toString() {
		return "StageReservationTime [timeCode=" + timeCode + ", stageNo=" + stageNo + "]";
	}

	public int getTimeCode() {
		return timeCode;
	}

	public void setTimeCode(int timeCode) {
		this.timeCode = timeCode;
	}

	public int getStageNo() {
		return stageNo;
	}

	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stageNo;
		result = prime * result + timeCode;
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
		StageReservationTime other = (StageReservationTime) obj;
		if (stageNo != other.stageNo)
			return false;
		if (timeCode != other.timeCode)
			return false;
		return true;
	}
	
	

}

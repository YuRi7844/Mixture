package com.buskstop.vo;

public class PremiumStageTime {

	private int optionNo;//공연장 대관 옵션번호
	private int timeCode;//1~24 - 0~24시를 한시간 단위로 숫자로 표현.
	
	public PremiumStageTime() {}

	public PremiumStageTime(int optionNo, int timeCode) {
		super();
		this.optionNo = optionNo;
		this.timeCode = timeCode;
	}

	public int getOptionNo() {
		return optionNo;
	}

	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}

	public int getTimeCode() {
		return timeCode;
	}

	public void setTimeCode(int timeCode) {
		this.timeCode = timeCode;
	}

	@Override
	public String toString() {
		return "PremiumStageTime [optionNo=" + optionNo + ", timeCode=" + timeCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + optionNo;
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
		PremiumStageTime other = (PremiumStageTime) obj;
		if (optionNo != other.optionNo)
			return false;
		if (timeCode != other.timeCode)
			return false;
		return true;
	}
	
}

package com.buskstop.vo;

import java.util.List;

public class PremiumStageOptionList {

	private List<PremiumStageOption> optionList;
	
	public PremiumStageOptionList() {}

	public PremiumStageOptionList(List<PremiumStageOption> optionList) {
		super();
		this.optionList = optionList;
	}

	public List<PremiumStageOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<PremiumStageOption> optionList) {
		this.optionList = optionList;
	}

	@Override
	public String toString() {
		return "PremiumStageOptionList [optionList=" + optionList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((optionList == null) ? 0 : optionList.hashCode());
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
		PremiumStageOptionList other = (PremiumStageOptionList) obj;
		if (optionList == null) {
			if (other.optionList != null)
				return false;
		} else if (!optionList.equals(other.optionList))
			return false;
		return true;
	}
}

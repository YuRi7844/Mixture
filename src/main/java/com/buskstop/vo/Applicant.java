package com.buskstop.vo;

import java.io.Serializable;
import java.sql.Date;

public class Applicant implements Serializable {
	// Date는 일단 sql 로 해두었음. 
	private int employNum;
	private String employTitle;
	private String employContent;
	private String employLocation;
	private Date employDate;
	private int employCost;
	private String employTalent;
	private User employUser; // artist의 회원정보 User type
	
	public Applicant() {}
	
	public Applicant(int employNum, String employTitle, String employContent, String employLocation, Date employDate,
			int employCost, String employTalent, User employUser) {
		this.employNum = employNum;
		this.employTitle = employTitle;
		this.employContent = employContent;
		this.employLocation = employLocation;
		this.employDate = employDate;
		this.employCost = employCost;
		this.employTalent = employTalent;
		this.employUser = employUser;
	}

	public int getEmployNum() {
		return employNum;
	}

	public void setEmployNum(int employNum) {
		this.employNum = employNum;
	}

	public String getEmployTitle() {
		return employTitle;
	}

	public void setEmployTitle(String employTitle) {
		this.employTitle = employTitle;
	}

	public String getEmployContent() {
		return employContent;
	}

	public void setEmployContent(String employContent) {
		this.employContent = employContent;
	}

	public String getEmployLocation() {
		return employLocation;
	}

	public void setEmployLocation(String employLocation) {
		this.employLocation = employLocation;
	}

	public Date getEmployDate() {
		return employDate;
	}

	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}

	public int getEmployCost() {
		return employCost;
	}

	public void setEmployCost(int employCost) {
		this.employCost = employCost;
	}

	public String getEmployTalent() {
		return employTalent;
	}

	public void setEmployTalent(String employTalent) {
		this.employTalent = employTalent;
	}

	public User getEmployUser() {
		return employUser;
	}

	public void setEmployUser(User employUser) {
		this.employUser = employUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employContent == null) ? 0 : employContent.hashCode());
		result = prime * result + employCost;
		result = prime * result + ((employDate == null) ? 0 : employDate.hashCode());
		result = prime * result + ((employLocation == null) ? 0 : employLocation.hashCode());
		result = prime * result + employNum;
		result = prime * result + ((employTalent == null) ? 0 : employTalent.hashCode());
		result = prime * result + ((employTitle == null) ? 0 : employTitle.hashCode());
		result = prime * result + ((employUser == null) ? 0 : employUser.hashCode());
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
		Applicant other = (Applicant) obj;
		if (employContent == null) {
			if (other.employContent != null)
				return false;
		} else if (!employContent.equals(other.employContent))
			return false;
		if (employCost != other.employCost)
			return false;
		if (employDate == null) {
			if (other.employDate != null)
				return false;
		} else if (!employDate.equals(other.employDate))
			return false;
		if (employLocation == null) {
			if (other.employLocation != null)
				return false;
		} else if (!employLocation.equals(other.employLocation))
			return false;
		if (employNum != other.employNum)
			return false;
		if (employTalent == null) {
			if (other.employTalent != null)
				return false;
		} else if (!employTalent.equals(other.employTalent))
			return false;
		if (employTitle == null) {
			if (other.employTitle != null)
				return false;
		} else if (!employTitle.equals(other.employTitle))
			return false;
		if (employUser == null) {
			if (other.employUser != null)
				return false;
		} else if (!employUser.equals(other.employUser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Applicant [employNum=" + employNum + ", employTitle=" + employTitle + ", employContent=" + employContent
				+ ", employLocation=" + employLocation + ", employDate=" + employDate + ", employCost=" + employCost
				+ ", employTalent=" + employTalent + ", employUser=" + employUser + "]";
	}
	
	
}

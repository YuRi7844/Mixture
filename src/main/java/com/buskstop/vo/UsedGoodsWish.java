package com.buskstop.vo;

import java.io.Serializable;

public class UsedGoodsWish implements Serializable {
	private int goodsWishNum;
	private String wishTitle;
	private String wishModel;
	private String wishContent;
	private User wishUser;
	
	public UsedGoodsWish() {}
	public UsedGoodsWish(int goodsWishNum, String wishTitle, String wishModel, String wishContent, User wishUser) {
		this.goodsWishNum = goodsWishNum;
		this.wishTitle = wishTitle;
		this.wishModel = wishModel;
		this.wishContent = wishContent;
		this.wishUser = wishUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + goodsWishNum;
		result = prime * result + ((wishContent == null) ? 0 : wishContent.hashCode());
		result = prime * result + ((wishModel == null) ? 0 : wishModel.hashCode());
		result = prime * result + ((wishTitle == null) ? 0 : wishTitle.hashCode());
		result = prime * result + ((wishUser == null) ? 0 : wishUser.hashCode());
		return result;
	}
	
	public int getGoodsWishNum() {
		return goodsWishNum;
	}
	public void setGoodsWishNum(int goodsWishNum) {
		this.goodsWishNum = goodsWishNum;
	}
	public String getWishTitle() {
		return wishTitle;
	}
	public void setWishTitle(String wishTitle) {
		this.wishTitle = wishTitle;
	}
	public String getWishModel() {
		return wishModel;
	}
	public void setWishModel(String wishModel) {
		this.wishModel = wishModel;
	}
	public String getWishContent() {
		return wishContent;
	}
	public void setWishContent(String wishContent) {
		this.wishContent = wishContent;
	}
	public User getWishUser() {
		return wishUser;
	}
	public void setWishUser(User wishUser) {
		this.wishUser = wishUser;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsedGoodsWish other = (UsedGoodsWish) obj;
		if (goodsWishNum != other.goodsWishNum)
			return false;
		if (wishContent == null) {
			if (other.wishContent != null)
				return false;
		} else if (!wishContent.equals(other.wishContent))
			return false;
		if (wishModel == null) {
			if (other.wishModel != null)
				return false;
		} else if (!wishModel.equals(other.wishModel))
			return false;
		if (wishTitle == null) {
			if (other.wishTitle != null)
				return false;
		} else if (!wishTitle.equals(other.wishTitle))
			return false;
		if (wishUser == null) {
			if (other.wishUser != null)
				return false;
		} else if (!wishUser.equals(other.wishUser))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UsedGoodsWish [goodsWishNum=" + goodsWishNum + ", wishTitle=" + wishTitle + ", wishModel=" + wishModel
				+ ", wishContent=" + wishContent + ", wishUser=" + wishUser + "]";
	}
	
	
}

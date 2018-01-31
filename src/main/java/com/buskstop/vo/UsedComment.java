package com.buskstop.vo;

import java.io.Serializable;

public class UsedComment implements Serializable{
	private int goodsNum;
	private String purchaseId;
	private String goodsContent;
	
	public UsedComment() {
	}

	public UsedComment(int goodsNum, String purchaseId, String goodsContent) {
		this.goodsNum = goodsNum;
		this.purchaseId = purchaseId;
		this.goodsContent = goodsContent;
	}

	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getGoodsContent() {
		return goodsContent;
	}

	public void setGoodsContent(String goodsContent) {
		this.goodsContent = goodsContent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsContent == null) ? 0 : goodsContent.hashCode());
		result = prime * result + goodsNum;
		result = prime * result + ((purchaseId == null) ? 0 : purchaseId.hashCode());
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
		UsedComment other = (UsedComment) obj;
		if (goodsContent == null) {
			if (other.goodsContent != null)
				return false;
		} else if (!goodsContent.equals(other.goodsContent))
			return false;
		if (goodsNum != other.goodsNum)
			return false;
		if (purchaseId == null) {
			if (other.purchaseId != null)
				return false;
		} else if (!purchaseId.equals(other.purchaseId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UsedComment [goodsNum=" + goodsNum + ", purchaseId=" + purchaseId + ", goodsContent=" + goodsContent
				+ "]";
	}
	
}

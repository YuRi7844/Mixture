package com.buskstop.vo;

import java.sql.Date;

public class UsedGoods {
	private int GoodsNum;
	private String goodsTitle; // 글제목
	private String goodsModel; // 모델명
	private String goodsBrand; // 브랜드
	private int goodsCost; // 가격
	private String goodsImage; // 사진
	private int goodsEa; // 수량
	private Date registerDate; // 등록일자 (sql로 했다)
	private String goodsComment;
	private User salesUser;
	
	public UsedGoods() {}

	public UsedGoods(int goodsNum, String goodsTitle, String goodsModel, String goodsBrand, int goodsCost,
			String goodsImage, int goodsEa, Date registerDate, String goodsComment, User salesUser) {
		GoodsNum = goodsNum;
		this.goodsTitle = goodsTitle;
		this.goodsModel = goodsModel;
		this.goodsBrand = goodsBrand;
		this.goodsCost = goodsCost;
		this.goodsImage = goodsImage;
		this.goodsEa = goodsEa;
		this.registerDate = registerDate;
		this.goodsComment = goodsComment;
		this.salesUser = salesUser;
	}

	public int getGoodsNum() {
		return GoodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		GoodsNum = goodsNum;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getGoodsModel() {
		return goodsModel;
	}

	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	public String getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	public int getGoodsCost() {
		return goodsCost;
	}

	public void setGoodsCost(int goodsCost) {
		this.goodsCost = goodsCost;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public int getGoodsEa() {
		return goodsEa;
	}

	public void setGoodsEa(int goodsEa) {
		this.goodsEa = goodsEa;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getGoodsComment() {
		return goodsComment;
	}

	public void setGoodsComment(String goodsComment) {
		this.goodsComment = goodsComment;
	}

	public User getSalesUser() {
		return salesUser;
	}

	public void setSalesUser(User salesUser) {
		this.salesUser = salesUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + GoodsNum;
		result = prime * result + ((goodsBrand == null) ? 0 : goodsBrand.hashCode());
		result = prime * result + ((goodsComment == null) ? 0 : goodsComment.hashCode());
		result = prime * result + goodsCost;
		result = prime * result + goodsEa;
		result = prime * result + ((goodsImage == null) ? 0 : goodsImage.hashCode());
		result = prime * result + ((goodsModel == null) ? 0 : goodsModel.hashCode());
		result = prime * result + ((goodsTitle == null) ? 0 : goodsTitle.hashCode());
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((salesUser == null) ? 0 : salesUser.hashCode());
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
		UsedGoods other = (UsedGoods) obj;
		if (GoodsNum != other.GoodsNum)
			return false;
		if (goodsBrand == null) {
			if (other.goodsBrand != null)
				return false;
		} else if (!goodsBrand.equals(other.goodsBrand))
			return false;
		if (goodsComment == null) {
			if (other.goodsComment != null)
				return false;
		} else if (!goodsComment.equals(other.goodsComment))
			return false;
		if (goodsCost != other.goodsCost)
			return false;
		if (goodsEa != other.goodsEa)
			return false;
		if (goodsImage == null) {
			if (other.goodsImage != null)
				return false;
		} else if (!goodsImage.equals(other.goodsImage))
			return false;
		if (goodsModel == null) {
			if (other.goodsModel != null)
				return false;
		} else if (!goodsModel.equals(other.goodsModel))
			return false;
		if (goodsTitle == null) {
			if (other.goodsTitle != null)
				return false;
		} else if (!goodsTitle.equals(other.goodsTitle))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (salesUser == null) {
			if (other.salesUser != null)
				return false;
		} else if (!salesUser.equals(other.salesUser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsedGoods [GoodsNum=" + GoodsNum + ", goodsTitle=" + goodsTitle + ", goodsModel=" + goodsModel
				+ ", goodsBrand=" + goodsBrand + ", goodsCost=" + goodsCost + ", goodsImage=" + goodsImage
				+ ", goodsEa=" + goodsEa + ", registerDate=" + registerDate + ", goodsComment=" + goodsComment
				+ ", salesUser=" + salesUser + "]";
	}
	
	
}

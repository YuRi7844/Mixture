package com.buskstop.vo;

import java.io.Serializable;

public class OrderDetail implements Serializable{
	private int orderNum;
	private String shoppingAddress;
	private int orderEa;
	private int purchaseCost;
	private String receiverPhoneNum;
	public OrderDetail() {
	}
	public OrderDetail(int orderNum, String shoppingAddress, int orderEa, int purchaseCost, String receiverPhoneNum) {
		this.orderNum = orderNum;
		this.shoppingAddress = shoppingAddress;
		this.orderEa = orderEa;
		this.purchaseCost = purchaseCost;
		this.receiverPhoneNum = receiverPhoneNum;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getShoppingAddress() {
		return shoppingAddress;
	}
	public void setShoppingAddress(String shoppingAddress) {
		this.shoppingAddress = shoppingAddress;
	}
	public int getOrderEa() {
		return orderEa;
	}
	public void setOrderEa(int orderEa) {
		this.orderEa = orderEa;
	}
	public int getPurchaseCost() {
		return purchaseCost;
	}
	public void setPurchaseCost(int purchaseCost) {
		this.purchaseCost = purchaseCost;
	}
	public String getReceiverPhoneNum() {
		return receiverPhoneNum;
	}
	public void setReceiverPhoneNum(String receiverPhoneNum) {
		this.receiverPhoneNum = receiverPhoneNum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderEa;
		result = prime * result + orderNum;
		result = prime * result + purchaseCost;
		result = prime * result + ((receiverPhoneNum == null) ? 0 : receiverPhoneNum.hashCode());
		result = prime * result + ((shoppingAddress == null) ? 0 : shoppingAddress.hashCode());
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
		OrderDetail other = (OrderDetail) obj;
		if (orderEa != other.orderEa)
			return false;
		if (orderNum != other.orderNum)
			return false;
		if (purchaseCost != other.purchaseCost)
			return false;
		if (receiverPhoneNum == null) {
			if (other.receiverPhoneNum != null)
				return false;
		} else if (!receiverPhoneNum.equals(other.receiverPhoneNum))
			return false;
		if (shoppingAddress == null) {
			if (other.shoppingAddress != null)
				return false;
		} else if (!shoppingAddress.equals(other.shoppingAddress))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderDetail [orderNum=" + orderNum + ", shoppingAddress=" + shoppingAddress + ", orderEa=" + orderEa
				+ ", purchaseCost=" + purchaseCost + ", receiverPhoneNum=" + receiverPhoneNum + "]";
	}
	
	
}

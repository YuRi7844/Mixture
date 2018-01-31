package com.buskstop.vo;

import java.io.Serializable;

public class Order implements Serializable{
	private int orderNum;
	private int goodsNum;
	private String consumerId;
	private int cancleCode;
	public Order() {
	}
	public Order(int orderNum, int goodsNum, String consumerId, int cancleCode) {
		this.orderNum = orderNum;
		this.goodsNum = goodsNum;
		this.consumerId = consumerId;
		this.cancleCode = cancleCode;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public int getCancleCode() {
		return cancleCode;
	}
	public void setCancleCode(int cancleCode) {
		this.cancleCode = cancleCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cancleCode;
		result = prime * result + ((consumerId == null) ? 0 : consumerId.hashCode());
		result = prime * result + goodsNum;
		result = prime * result + orderNum;
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
		Order other = (Order) obj;
		if (cancleCode != other.cancleCode)
			return false;
		if (consumerId == null) {
			if (other.consumerId != null)
				return false;
		} else if (!consumerId.equals(other.consumerId))
			return false;
		if (goodsNum != other.goodsNum)
			return false;
		if (orderNum != other.orderNum)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [orderNum=" + orderNum + ", goodsNum=" + goodsNum + ", consumerId=" + consumerId + ", cancleCode="
				+ cancleCode + "]";
	}
	
}

package com.buskstop.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class PremiumStageReservation {

		private int reservationNo; //예약신청 번호
		private String reservationUserId; //예약신청자 ID
		private int establishNo;//사업장번호
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date reservationRegTime;//예약 신청자 신청시간
		
		private PremiumStageOption option; //부모타입 변수
		private int optionNo;//예약옵션번호
		
		public PremiumStageReservation() {}

		public int getReservationNo() {
			return reservationNo;
		}

		public PremiumStageReservation(int reservationNo, String reservationUserId, int establishNo,
				Date reservationRegTime, PremiumStageOption option, int optionNo) {
			super();
			this.reservationNo = reservationNo;
			this.reservationUserId = reservationUserId;
			this.establishNo = establishNo;
			this.reservationRegTime = reservationRegTime;
			this.option = option;
			this.optionNo = optionNo;
		}

		public String getReservationUserId() {
			return reservationUserId;
		}

		public void setReservationUserId(String reservationUserId) {
			this.reservationUserId = reservationUserId;
		}

		public int getEstablishNo() {
			return establishNo;
		}

		public void setEstablishNo(int establishNo) {
			this.establishNo = establishNo;
		}

		public Date getReservationRegTime() {
			return reservationRegTime;
		}

		public void setReservationRegTime(Date reservationRegTime) {
			this.reservationRegTime = reservationRegTime;
		}

		public PremiumStageOption getOption() {
			return option;
		}

		public void setOption(PremiumStageOption option) {
			this.option = option;
		}

		public int getOptionNo() {
			return optionNo;
		}

		public void setOptionNo(int optionNo) {
			this.optionNo = optionNo;
		}

		public void setReservationNo(int reservationNo) {
			this.reservationNo = reservationNo;
		}

		@Override
		public String toString() {
			return "PremiumStageReservation [reservationNo=" + reservationNo + ", reservationUserId="
					+ reservationUserId + ", establishNo=" + establishNo + ", reservationRegTime=" + reservationRegTime
					+ ", option=" + option + ", optionNo=" + optionNo + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + establishNo;
			result = prime * result + ((option == null) ? 0 : option.hashCode());
			result = prime * result + optionNo;
			result = prime * result + reservationNo;
			result = prime * result + ((reservationRegTime == null) ? 0 : reservationRegTime.hashCode());
			result = prime * result + ((reservationUserId == null) ? 0 : reservationUserId.hashCode());
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
			PremiumStageReservation other = (PremiumStageReservation) obj;
			if (establishNo != other.establishNo)
				return false;
			if (option == null) {
				if (other.option != null)
					return false;
			} else if (!option.equals(other.option))
				return false;
			if (optionNo != other.optionNo)
				return false;
			if (reservationNo != other.reservationNo)
				return false;
			if (reservationRegTime == null) {
				if (other.reservationRegTime != null)
					return false;
			} else if (!reservationRegTime.equals(other.reservationRegTime))
				return false;
			if (reservationUserId == null) {
				if (other.reservationUserId != null)
					return false;
			} else if (!reservationUserId.equals(other.reservationUserId))
				return false;
			return true;
		}
}

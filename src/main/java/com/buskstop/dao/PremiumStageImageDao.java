package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.PremiumStageImage;

public interface PremiumStageImageDao {
	/**
	 * 프리미엄 공급장 이미지정보를 저장한다.
	 * @param image
	 * @return
	 */
	int insertImage(PremiumStageImage image);
	
	/**
	 * 사업장번호로 이미지 정보 select dao
	 * @param establishNum
	 * @return
	 */
	List<String> selectImageByEstablishNum(int establishNum);
	
	/**
	 * 사업자 번호로 기존의 이미지 delete하기 위한 dao.
	 * @param establishNum
	 * @return
	 */
	int deleteImageByEstablishNum(int establishNum);
}

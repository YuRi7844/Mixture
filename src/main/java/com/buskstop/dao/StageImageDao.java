package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.StageImage;

public interface StageImageDao {

	/**
	 * 공연장사진 등록
	 * @param stageImage
	 * @return
	 */
	int insertStageImage(StageImage stageImage);
	
	/**
	 * 공연장사진 공연장 번호로 삭제
	 * @param stageNo
	 * @return
	 */
	int deleteStageImageByStageNo(int stageNo);
	
	/**
	 * 공연장사진 공연장 번호로 조회
	 * @param stageNo
	 * @return
	 */
	List<StageImage> selectStageImageByStageNo(int stageNo);
	
	/**
	 * 공연장사진 공연장사진 번호로 삭제
	 * @param stageImageNo
	 * @return
	 */
	int deleteStageImageByStageImageNo(int stageImageNo);
}

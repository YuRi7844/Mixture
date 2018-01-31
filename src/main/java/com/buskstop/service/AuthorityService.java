package com.buskstop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.buskstop.vo.Authority;

@Service
public interface AuthorityService {
	
	/**
	 * 사용자 id로 사용자에게 아티스트 권한이 있는지 조회
	 * @param userId
	 * @return
	 */
	boolean readArtistAutoritiesByUserId(String userId);
	
	/**
	 * 사용자 id로 공급자 권한이 있는지 조회
	 * @param userId
	 * @return
	 */
	boolean checkStageAuthorityByUserId(String userId);
}

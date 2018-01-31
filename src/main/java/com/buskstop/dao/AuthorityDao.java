package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.Authority;
import com.buskstop.vo.Performance;

public interface AuthorityDao {
	
	/**
	 * 권한테이블에 로우를 추가하는 Dao
	 * @param authority
	 * @return
	 */
	int insertAuthority(Authority authority);

	/**
	 * 사용자가 가지는 모든 권한 조회
	 * @param userId
	 * @return
	 */
	List<Authority> selectAuthoritiesByUserId(String userId);

	/**
	 * 사용자id로 사용자가 가지는 권한 중 artist를 조회
	 * @param userId
	 * @return
	 */
	boolean selectArtistAuthoritiesByUserId(String userId);
	/**
	 * 사용자 권한 수정
	 * @param authority
	 * @return
	 */
	int updateAuthority(Authority authority);
	
	/**
	 * id로 Authority row delete
	 * @param id
	 * @return int
	 */
	int deleteAuthorityById(String id);
	
	/**
	 * id로 프리미엄공급자 권한을 조회한다.
	 * @param userId
	 * @return
	 */
	boolean selectPremiumAuthorityByUserId(String userId);
}

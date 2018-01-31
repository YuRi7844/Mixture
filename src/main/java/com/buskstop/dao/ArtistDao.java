package com.buskstop.dao;

import com.buskstop.vo.Artist;

public interface ArtistDao {
	int insertArtist(Artist artist);
	
	/**
	 * 사용자 아이디로 아티스트 프로필 검색
	 * @param userId
	 * @return
	 */
	Artist selectArtistByUserId(String userId) ;
	
	int updateArtist(Artist artist);
}

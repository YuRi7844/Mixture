package com.buskstop.service;

import java.util.Map;

import com.buskstop.vo.Artist;

public interface ArtistService {
	void registerArtist(Artist artist);
	
	Artist readArtistByUserId(String userId);
	
	int updateArtist(Artist artist);
	
	/**
	 * 추천아티스트 정보 제공해주는 service.
	 * @return
	 */
	Map<String, Object> recommendArtist();
}

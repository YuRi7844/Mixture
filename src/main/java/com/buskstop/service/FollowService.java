package com.buskstop.service;

import java.util.List;

import com.buskstop.vo.Artist;
import com.buskstop.vo.Follow;

public interface FollowService {
	/**
	 * userId로 팔로우한 artistId를 List로 조회
	 * @param userId
	 * @return
	 */
	boolean searchFollowing(String userId, String followingId);
	
	/**
	 * 팔로우 service
	 */
	void doFollow(String userId, String followingId);
	
	/**
	 * 언팔로우 service
	 */
	void unFollow(String userId, String followingId);
	
	/**
	 * userId로 follow한 아티스트 정보를 제공하는 service.
	 * @param userId
	 * @return
	 */
	List<Artist> followArtistList(String userId);
	
	/**
	 * 날 팔로우 한 사람들을 조회
	 * @param FollowerId
	 * @return
	 */
	List<Follow> selectFollowByFollowerId(String FollowerId);
}

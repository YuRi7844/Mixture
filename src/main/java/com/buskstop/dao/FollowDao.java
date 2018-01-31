package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.Artist;
import com.buskstop.vo.Follow;

public interface FollowDao {
	/**
	 * follow정보 insert
	 * 
	 * @param follow
	 * @return
	 */
	int insertFollow(Follow follow);

	/**
	 * follow 정보 delete
	 * 
	 * @param follow
	 * @return
	 */
	int deleteFollow(Follow follow);

	/**
	 * userId로 follow 정보 전체조회
	 * 
	 * @param userId
	 * @return
	 */
	List<Follow> selectFollowByUserId(String userId);

	/**
	 * userId로 팔로우 정보를 select
	 * 
	 * @param userId
	 * @return
	 */
	List<Artist> selectFollowArtistById(String userId);

	/**
	 * 아티스트의 팔로우수를 내림차순으로 정렬해서 select.
	 * 
	 * @return
	 */
	List<Artist> selectArtistFollowCount();

	/**
	 * 날 팔로우한 사람들을 조회
	 * 
	 * @param followerId
	 * @return
	 */
	List<Follow> selectFollowByFollowerId(String followerId);
}

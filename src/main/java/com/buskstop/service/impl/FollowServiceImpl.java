package com.buskstop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buskstop.dao.FollowDao;
import com.buskstop.service.FollowService;
import com.buskstop.vo.Artist;
import com.buskstop.vo.Follow;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	FollowDao dao;
	
	@Override
	public void doFollow(String userId, String followingId) {
		dao.insertFollow(new Follow(userId, followingId));
	}

	@Override
	public void unFollow(String userId, String followingId) {
		dao.deleteFollow(new Follow(userId, followingId));
	}

	@Override
	public boolean searchFollowing(String userId, String followingId) {
		List<Follow> list = dao.selectFollowByUserId(userId);
		if(list==null && list.size()==0) {
			return false;
		}
		for(Follow follow:list) {
			if(follow.getFollowerId().equals(followingId)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Artist> followArtistList(String userId){
		return dao.selectFollowArtistById(userId);
	}
	
	@Override
	public List<Follow> selectFollowByFollowerId(String followerId){
		return dao.selectFollowByFollowerId(followerId);
	}
}

package com.buskstop.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.FollowDao;
import com.buskstop.vo.Artist;
import com.buskstop.vo.Follow;

@Repository
public class FollowDaoImpl implements FollowDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.followMapper."+id;
	}
	
	@Override
	public int insertFollow(Follow follow) {
		return session.insert(makeSqlId("insertFollow"), follow);
	}

	@Override
	public int deleteFollow(Follow follow) {
		return session.delete(makeSqlId("deleteFollow"),follow);
	}

	@Override
	public List<Follow> selectFollowByUserId(String userId) {
		return session.selectList(makeSqlId("selectFollowByUserId"), userId);
	}

	@Override
	public List<Artist> selectFollowArtistById(String userId) {
		return session.selectList(makeSqlId("selectFollowArtistById"), userId);
	}

	@Override
	public List<Artist> selectArtistFollowCount() {
		return session.selectList(makeSqlId("selectArtistFollowCount"));
	}
	
	@Override
	public List<Follow> selectFollowByFollowerId(String followerId){
		return session.selectList(makeSqlId("selectFollowByFollowerId"), followerId);
	}
	
}

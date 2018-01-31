package com.buskstop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buskstop.dao.ArtistDao;
import com.buskstop.dao.AuthorityDao;
import com.buskstop.dao.FollowDao;
import com.buskstop.dao.PerformanceDao;
import com.buskstop.dao.VideoDao;
import com.buskstop.service.ArtistService;
import com.buskstop.vo.Artist;
import com.buskstop.vo.Authority;

@Service
public class ArtistServiceImpl implements ArtistService{

	@Autowired
	private ArtistDao artistDao;
	
	@Autowired
	private AuthorityDao authorityDao;
	
	@Autowired
	private FollowDao followDao;
	
	@Autowired
	private VideoDao videoDao;
	
	@Autowired
	private PerformanceDao performanceDao;
	
	@Override
	public void registerArtist(Artist artist) {
		artistDao.insertArtist(artist);
		authorityDao.insertAuthority(new Authority(artist.getArtistId(),"ROLE_ARTIST"));
	}

	@Override
	public Artist readArtistByUserId(String userId) {
		return artistDao.selectArtistByUserId(userId);
	}

	@Override
	public int updateArtist(Artist artist) {
		return artistDao.updateArtist(artist);
	}

	@Override
	public Map<String, Object> recommendArtist() {
		// 필요한 정보 : 아티스트의 게시물 수, 팔로워 수.
		
		// 팔로우수를 담은 아티스트의 정보.
		List<Artist> list = followDao.selectArtistFollowCount();
		
		List<Artist> returnList = new ArrayList<>();
		if(list.size()<10) {
			for(int i=0; i<list.size(); i++) {
				// 팔로우수를 포함한 모든 아티스트 정보를 새로 넣어서 list에 담는다.
				Artist artist = list.get(i);
				//TEST
				
				Artist editArtist = artistDao.selectArtistByUserId(artist.getArtistId());
				// 없는 아티스트 정보이면 break한다.
				if(editArtist==null) {
					break;
				}
				editArtist.setFollowCount(artist.getFollowCount());
				returnList.add(editArtist);
			}
		} else {
			for(int i=0; i<10;i++) {
				Artist artist = list.get(i);
				Artist editArtist = artistDao.selectArtistByUserId(artist.getArtistId());
				
				//없는 아티스트 정보이면 break;
				if(editArtist==null) {
					break;
				}
				
				editArtist.setFollowCount(artist.getFollowCount());
				returnList.add(editArtist);
			}
		}
		if(returnList.size() != 0) {
			int i = (int)(Math.random()*(returnList.size()-1));
			Artist artist = returnList.get(i);
			
			// 아티스트의 게시물 수.
			int videoCount =  videoDao.selectUserVideoCount(artist.getArtistId());
			int performanceCount = performanceDao.selectArtistPerformanceCountById(artist.getArtistId());
			
			// 값을 전달할 map.
			HashMap<String, Object> map = new HashMap<>();
			map.put("artist", artist);
			map.put("registCount", videoCount+performanceCount);
			
			return map;
		}
		else {
			return null;
		}
	}
	
	
	
	
}

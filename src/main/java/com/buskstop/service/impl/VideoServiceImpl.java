package com.buskstop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buskstop.dao.VideoCommentDao;
import com.buskstop.dao.VideoDao;
import com.buskstop.dao.VideoLikeDao;
import com.buskstop.service.VideoService;

import com.buskstop.vo.Performance;
import com.buskstop.vo.Video;
import com.buskstop.vo.VideoLike;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private VideoLikeDao videoLikeDao;
	
	@Autowired
	private VideoDao dao;
	
	@Autowired
	private VideoCommentDao commentDao;
	
	////////////좋아요////////////
	// 좋아요 추가.
	@Override 
	public void plusLike(VideoLike like) {
		videoLikeDao.insertVideoLike(like);
	}
	
	// 좋아요 삭제.
	@Override 
	public void deleteLike(VideoLike like) {
		videoLikeDao.deleteVideoLike(like);
	}
	
	@Override
	public List<Video> selectVideoByVideoLikeId(String id){
		return videoLikeDao.selectVideoByVideoLikeId(id);
	}
	
	///////////동영상///////////
	
	//동영상 등록
	@Override 
	public int insertVideo(Video video) {
		return dao.insertVideo(video);
	}
	
	// 동영상 수정
	@Override
	public int updateVideo(Video update) {
		return dao.updateVideo(update);
	}

	//동영상 삭제
	@Override
	@Transactional
	public int deleteVideoByVideoNum(int videoNo) {
		videoLikeDao.deleteVideoLikeByVideoNo(videoNo);
		commentDao.deleteVideoCommentByVideoNo(videoNo);
		return dao.deleteVideoByVideoNum(videoNo);
	}

	//----------동영상 조회----------------------------
	
	//동영상번호로 동영상 조회
	@Override 
	public Video viewVideoByVideoNo(int videoNo) {
		return dao.selectVideoByVideoNo(videoNo);
	}
	
	// 동영상 번호로 좋아요 누른 정보 조회.
	@Override 
	public List<VideoLike> selectLikeUserByNum(int num){
		return videoLikeDao.selectLikeUserByVideoNum(num);
	}

	// 모든 동영상 조회.
	@Override 
	public List<Video> viewAllVideo (String category) {
		return dao.selectAllVideoByCategory(category);
	}

	// 제목으로 동영상 조회.
	@Override
	public List<Video> viewVideoByTitleAndCategory(String videoCategory, String videoTitle) {
		return dao.selectVideoByTitleAndCategory(videoCategory, videoTitle);
	}

	// 아티스트로 동영상 조회.
	@Override
	public List<Video> viewVideoByArtistAndCategory(String videoCategory, String videoArtist) {
		return dao.selectVideoByArtistAndCategory(videoCategory, videoArtist);
	}

	// 게시자 id로 동영상 조회.
	@Override
	public List<Video> viewVideoByUserIdAndCategory(String videoCategory, String videoUserId) {
		return dao.selectVideoByUserIdAndCategory(videoCategory, videoUserId);
	}

	// 내용(추가글)으로 동영상 조회.
	@Override
	public List<Video> viewVideoByContentAndCategory(String videoCategory, String videoContent) {
		return dao.selectVideoByContentAndCategory(videoCategory, videoContent);
	}
	
	// 게시자 id로만 동영상 조회
	@Override
	public List<Video> viewVideoByArtist(String videoArtist){
		return dao.selectVideoByArtistId(videoArtist);
	}

	/******* admin용 삭제 후 조회 service ******/

	@Override
	@Transactional
	public List<Video> deleteVideoAndSelect(int videoNo) {
		
		// commentDao.delete (video에 달려 있는 댓글을 모두 지우자.)
		commentDao.deleteVideoCommentByVideoNo(videoNo);
		// 좋아요도 지워야지.
		videoLikeDao.deleteVideoLikeByVideoNo(videoNo);
		
		// video 정보 가져오고 지우고 보내기
		Video video = dao.selectVideoByVideoNo(videoNo);
		dao.deleteVideoByVideoNum(videoNo);
		
		return dao.selectAllVideoByCategory(video.getVideoCategory());
	}
	
	// 모든 video 정보 (where 없이 전체)

	
	public List<Video> selectVideo(){
		return dao.selectVideo();
	}

	@Override
	public List<Video> selectTopLikeVideo() {
		List<Video> list = dao.selectVideoByLikeCount();
		for(Video v : list) {
			System.out.println(v);
		}
		System.out.println(list.size());
		
		List<Video> returnList = new ArrayList<>();
		if(list.isEmpty()) {
			return null;
		}
		
		if (list.size() < 3) {
			for (int i = 0; i < list.size(); i++) {
				int num = list.get(i).getVideoNo();
				int likeCount = list.get(i).getLikeCount();
				System.out.println(num);
				System.out.println(likeCount);
				Video video = dao.selectVideoByVideoNo(num);
				System.out.println(video);
				video.setLikeCount(likeCount);
				returnList.add(video);
			}
		} else {
			for (int i = 0; i < 3; i++) {
				int num = list.get(i).getVideoNo();
				int likeCount = list.get(i).getLikeCount();
				Video video= dao.selectVideoByVideoNo(num);
				video.setLikeCount(likeCount);
				returnList.add(video);
			}
		}

		return returnList;
	}
	
	public List<Video> selectMyVideo(String videoUserId){
		return dao.selectMyVideo(videoUserId);
	}

}

package com.buskstop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.HelpCommentDao;
import com.buskstop.service.HelpCommentService;
import com.buskstop.vo.HelpComment;

@Repository
public class HelpCommentServiceImpl implements HelpCommentService{

	@Autowired
	HelpCommentDao dao;

	@Override
	public int insertHelpComment(HelpComment helpComment) {
		return dao.insertHelpComment(helpComment);
	}

	@Override
	public int deleteHelpComment(int helpCommentNo) {
		return dao.deleteHelpComment(helpCommentNo);
	}

	@Override
	public int updateHelpComment(HelpComment helpComment) {
		return dao.updateHelpComment(helpComment);
	}

	@Override
	public List<HelpComment> selectAllHelpComment() {
		return dao.selectAllHelpComment();
	}

	@Override
	public List<HelpComment> selectHelpCommentByUserId(String helpUserId) {
		return dao.selectHelpCommentByUserId(helpUserId);
	}

	@Override
	public List<HelpComment> selectHelpCommentByComment(String search) {
		return dao.selectHelpCommentByComment(search);
	}

	@Override
	public List<HelpComment> selectHelpCommentByHelpNo(int helpNo) {
		return dao.selectHelpCommentByHelpNo(helpNo);
	}
}

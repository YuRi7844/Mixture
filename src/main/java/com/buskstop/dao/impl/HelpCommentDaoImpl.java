package com.buskstop.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.HelpCommentDao;
import com.buskstop.vo.HelpComment;

@Repository
public class HelpCommentDaoImpl implements HelpCommentDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.helpCommentMapper."+id;
	}

	@Override
	public int insertHelpComment(HelpComment helpComment) {
		return session.insert(makeSqlId("insertHelpComment"), helpComment);
	}

	@Override
	public int deleteHelpComment(int helpCommentNo) {
		return session.delete(makeSqlId("deleteHelpComment"), helpCommentNo);
	}

	@Override
	public int updateHelpComment(HelpComment helpComment) {
		return session.update(makeSqlId("updateHelpComment"), helpComment);
	}

	@Override
	public List<HelpComment> selectAllHelpComment() {
		return session.selectList(makeSqlId("selectAllHelpComment"));
	}

	@Override
	public List<HelpComment> selectHelpCommentByUserId(String helpUserId) {
		return session.selectList(makeSqlId("selectHelpCommentByUserId"), helpUserId);
	}

	@Override
	public List<HelpComment> selectHelpCommentByComment(String search) {
		return session.selectList(makeSqlId("selectHelpCommentByComment"), search);
	}

	@Override
	public List<HelpComment> selectHelpCommentByHelpNo(int helpNo) {
		return session.selectList(makeSqlId("selectHelpCommentByHelpNo"), helpNo);
	}

}

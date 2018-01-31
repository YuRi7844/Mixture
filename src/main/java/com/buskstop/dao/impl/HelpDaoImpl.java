package com.buskstop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.HelpDao;
import com.buskstop.vo.Help;

@Repository
public class HelpDaoImpl implements HelpDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String makeSqlId(String id) {
		return "com.buskstop.config.mybatis.mapper.helpMapper." + id;
	}
	
	/********************************* insert Dao *********************************/
	@Override
	public void insertHelp() {
		System.out.println("Log: HelpDaoImpl.java -> helpMapper.Xml id=\"insertHelp\" 호출");
	}
	
	@Override
	public int insertHelp(Help help) {
		int i = session.insert(makeSqlId("insertHelp"), help);
		System.out.println(i);
		return i;
	}

	@Override
	public Help selectHelpByHelpNum(int helpNum) {
		System.out.println("오냐"+helpNum);
		return session.selectOne(makeSqlId("selectHelpByHelpNum"), helpNum);
	}
	
	@Override
	public int updateHelp(Help help) {
		return session.update(makeSqlId("updateHelp"),help);
	}
	
	@Override
	public int deleteHelpByHelpNum(int helpNum) {
		return session.delete(makeSqlId("deleteHelpByHelpNum"),helpNum);
	}
	
	@Override
	public List<Help> selectAllHelp(int beginItemNum, int endItemNum){
		HashMap<String, Integer> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		
		return session.selectList(makeSqlId("selectAllHelp"), map);
	}
	
	@Override
	public int selectAllHelpCount() {
		return session.selectOne(makeSqlId("selectAllHelpCount"));
	}

	@Override
	public List<Help> selectHelpByHelpTitle(int beginItemNum, int endItemNum, String helpTitle) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("helpTitle", helpTitle);
		
		return session.selectList(makeSqlId("selectHelpByHelpTitle"), map);
	}

	@Override
	public int countHelpByHelpTitle(String helpTitle) {
		return session.selectOne(makeSqlId("countHelpByHelpTitle"), helpTitle);
	}

	@Override
	public List<Help> selectHelpByHelpContent(int beginItemNum, int endItemNum, String helpContent) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("helpContent", helpContent);
		
		return session.selectList(makeSqlId("selectHelpByHelpContent"), map);
	}

	@Override
	public int countHelpByHelpContent(String helpContent) {
		return session.selectOne(makeSqlId("countHelpByHelpContent"), helpContent);
	}

	@Override
	public List<Help> selectHelpByHelpUserId(int beginItemNum, int endItemNum, String helpUserId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("begin", beginItemNum);
		map.put("end", endItemNum);
		map.put("helpUserId", helpUserId);
		
		return session.selectList(makeSqlId("selectHelpByHelpUserId"), map);
	}

	@Override
	public int countHelpByHelpUserId(String helpUserId) {
		return session.selectOne(makeSqlId("countHelpByHelpUserId"), helpUserId);
	}
	
	@Override
	public List<Help> selectMyHelp(String helpUserId){
		return session.selectList(makeSqlId("selectMyHelp"), helpUserId);
	}

}

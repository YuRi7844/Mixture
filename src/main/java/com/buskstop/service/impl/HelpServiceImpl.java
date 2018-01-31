package com.buskstop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buskstop.common.util.PagingBean;
import com.buskstop.dao.HelpDao;
import com.buskstop.service.HelpService;
import com.buskstop.vo.Help;

@Service
public class HelpServiceImpl implements HelpService{
	
	@Autowired
	private HelpDao dao;
		
	@Override
	public void insertHelp() {
		System.out.println("Log: HelpServiceImpl.java를 호출하였습니다.");
		System.out.println("helpMapper.xml 호출");
		dao.insertHelp();
	}
	
	@Override
	public void insertHelp(Help help) {
		System.out.println("Log: 서비스 호출");
		System.out.println("Log: HelpServiceImpl.java를 호출하였습니다.");
		System.out.println("Log: 서비스 파라미터"+help);
		dao.insertHelp(help);
	}

	@Override
	public Help selectHelpByHelpNum(int helpNum) {
		System.out.println("서비스"+helpNum);
		return dao.selectHelpByHelpNum(helpNum);
	}
	
	@Override 
	public void updateHelp(Help help) {
		dao.updateHelp(help);
	}
	
	@Override
	public int deleteHelpByHelpNum(int helpNum) {
		return dao.deleteHelpByHelpNum(helpNum);
	}

	@Override
	public Map<String, Object> selectAllHelp(int page) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		//PagingBean 생성
		PagingBean pb = new PagingBean(dao.selectAllHelpCount(), page);
		
		map.put("pageBean", pb);
		List<Help> list = dao.selectAllHelp(pb.getBeginItemInPage(), pb.getEndItemInPage());
		
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> selectHelpByHelpTitle(int page, String helpTitle) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		PagingBean pb = new PagingBean(dao.countHelpByHelpTitle(helpTitle), page);
		map.put("pageBean", pb);
		
		List<Help> list = dao.selectHelpByHelpTitle(pb.getBeginItemInPage(), pb.getEndItemInPage(), helpTitle);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> selectHelpByHelpContent(int page, String helpContent) {

		HashMap<String, Object> map = new HashMap<>();
		
		PagingBean pb = new PagingBean(dao.countHelpByHelpContent(helpContent), page);
		map.put("pageBean", pb);
		
		List<Help> list = dao.selectHelpByHelpContent(pb.getBeginItemInPage(), pb.getEndItemInPage(), helpContent);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> selectHelpByHelpUserId(int page, String helpUserId) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		PagingBean pb = new PagingBean(dao.countHelpByHelpUserId(helpUserId), page);
		map.put("pageBean", pb);
		
		List<Help> list = dao.selectHelpByHelpUserId(pb.getBeginItemInPage(), pb.getEndItemInPage(), helpUserId);
		map.put("list", list);
		
		return map;
	}

	@Override
	public List<Help> selectMyHelp(String helpUserId){
		return dao.selectMyHelp(helpUserId);
	}
}

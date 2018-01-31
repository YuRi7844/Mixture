package com.buskstop.service;

import java.util.List;
import java.util.Map;

import com.buskstop.vo.Help;

public interface HelpService {
	
	void insertHelp();
	
	void insertHelp(Help help);

	Help selectHelpByHelpNum(int helpNum);

	void updateHelp(Help help);

	int deleteHelpByHelpNum(int helpNum);
	
	/**
	 * 고객센터 전체 조회 페이징
	 * @param page
	 * @return
	 */
	Map<String, Object> selectAllHelp(int page);
	
	/**
	 * 제목으로 검색 페이징
	 * @param page
	 * @param helpTitle
	 * @return
	 */
	Map<String, Object> selectHelpByHelpTitle(int page, String helpTitle);
	
	/**
	 * 내용으로 검색 페이징
	 * @param page
	 * @param helpContent
	 * @return
	 */
	Map<String, Object> selectHelpByHelpContent(int page, String helpContent);
	
	/**
	 * 작성자로 검색 페이징
	 * @param page
	 * @param helpUserId
	 * @return
	 */
	Map<String, Object> selectHelpByHelpUserId(int page, String helpUserId);

	/**
	 * 내가 작성한 글
	 * @param helpUserId
	 * @return
	 */
	List<Help> selectMyHelp(String helpUserId);
}

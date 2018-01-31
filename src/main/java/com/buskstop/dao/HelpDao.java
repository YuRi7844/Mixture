package com.buskstop.dao;

import java.util.List;

import com.buskstop.vo.Help;

public interface HelpDao {
	
	void insertHelp();
	
	int insertHelp(Help help);
	
	Help selectHelpByHelpNum(int helpNum);

	int deleteHelpByHelpNum(int helpNum);

	int updateHelp(Help help);
	
	/**
	 * 모든 고객센터 조회 페이징
	 * @param beginItemNum
	 * @param endItemNum
	 * @return
	 */
	List<Help> selectAllHelp(int beginItemNum, int endItemNum);
	
	/**
	 * 모든 고객센터 카운트
	 * @return
	 */
	int selectAllHelpCount();
	
	/**
	 * 제목으로 검색 페이징
	 * @param beginItemNum
	 * @param endItemNum
	 * @param helpTitle
	 * @return
	 */
	List<Help> selectHelpByHelpTitle(int beginItemNum, int endItemNum, String helpTitle);
	
	/**
	 * 검색으로 조회 count
	 * @param helpTitle
	 * @return
	 */
	int countHelpByHelpTitle(String helpTitle);
	
	/**
	 * 내용으로 검색 페이징
	 * @param beginItemNum
	 * @param endItemNum
	 * @param helpContent
	 * @return
	 */
	List<Help> selectHelpByHelpContent(int beginItemNum, int endItemNum, String helpContent);

	/**
	 * 내용으로 조회 count
	 * @param helpContent
	 * @return
	 */
	int countHelpByHelpContent(String helpContent);

	/**
	 * 작성자로 검색 페이징
	 * @param beginItemNum
	 * @param endItemNum
	 * @param helpUserId
	 * @return
	 */
	List<Help> selectHelpByHelpUserId(int beginItemNum, int endItemNum, String helpUserId);

	/**
	 * 작성자로 검색 count
	 * @param helpUserId
	 * @return
	 */
	int countHelpByHelpUserId(String helpUserId);

	/**
	 * 내가 작성한 글
	 * @param helpUserId
	 * @return
	 */
	List<Help> selectMyHelp(String helpUserId);
}

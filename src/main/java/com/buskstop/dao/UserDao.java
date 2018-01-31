package com.buskstop.dao;

import java.util.HashMap;
import java.util.List;

import com.buskstop.vo.User;

public interface UserDao {
	/**
	 * 새로운 user data 등록
	 * @param user
	 * @return
	 */
	int insertUser(User user);
	
	/**
	 * id로 user 정보 객체를 select
	 * @param id
	 * @return
	 */
	User selectUserById(String id);
	
	/**
	 * user table update
	 * @param user
	 * @return
	 */
	int updateUser(User user);
	
	/**
	 * user의 DROP_CHECK UPDATE
	 * @param id
	 * @return
	 */
	int dropUpdateUserById(String id);
	
	/**
	 * user의 drop_check column 조회
	 * @param id
	 * @return
	 */
	int selectDropById(String id);
	
	/**
	 * Email로 User 정보 select
	 * @param email
	 * @return
	 */
	String selectUserIdByEmail(String email);
	
	/**
	 * 모든 유저정보 select (admin)
	 * @return
	 */
	List<User> selectAllUserAndAuthority();
	
	/**
	 * 검색 조건에 맞는 유저정보 select (admin)
	 * @param map
	 * @return
	 */
	List<User> selectByAuthorityAndUserId(HashMap<String,String> map);
}

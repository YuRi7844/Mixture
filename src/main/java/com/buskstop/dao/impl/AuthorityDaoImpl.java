package com.buskstop.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buskstop.dao.AuthorityDao;
import com.buskstop.vo.Authority;

@Repository
public class AuthorityDaoImpl implements AuthorityDao{

	@Autowired
	private SqlSessionTemplate session;

	private String makeSqlId(String id){
		return "com.buskstop.config.mybatis.mapper.authorityMapper."+id;
	}
	
	@Override
	public int insertAuthority(Authority authority) {
		return session.insert(makeSqlId("insertAuthority"),authority); 
	}
	
	@Override
	public int updateAuthority(Authority authority) {
		return session.update(makeSqlId("updateAuthority"),authority);
	}
	
	@Override
	public List<Authority> selectAuthoritiesByUserId(String userId) {
		return session.selectList(makeSqlId("selectAuthoritiesByUserId"), userId);
	}
	
	@Override
	public boolean selectArtistAuthoritiesByUserId(String userId) {
		Authority autority = new Authority(userId, "ROLE_ARTIST");
		Authority result = session.selectOne(makeSqlId("selectAuthority"), autority);
		if(result != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int deleteAuthorityById(String id) {
		return session.delete(makeSqlId("deleteAuthorityById"), id);
	}

	@Override
	public boolean selectPremiumAuthorityByUserId(String userId) {
		Authority authority =  session.selectOne(makeSqlId("selectAuthority"), new Authority(userId, "ROLE_PRODUCER"));
		if(authority==null) {
			return false;
		} else { 
			return true;
		}
	}
	
	

}

package com.buskstop.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.buskstop.dao.AuthorityDao;
import com.buskstop.dao.UserDao;
import com.buskstop.vo.Authority;
import com.buskstop.vo.User;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthorityDao authorutyDao;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// id를 통해 User 조회
		String id = authentication.getName();
		User user = userDao.selectUserById(id);
		
		// ID CHECK
		if (user == null) {
			throw new UsernameNotFoundException("ID를 확인해주세요.");
		}
		
		// 탈퇴유무 확인
		if(userDao.selectDropById(user.getUserId())==1) {
			throw new UsernameNotFoundException("탈퇴한 회원입니다. 재가입해주세요.");
		}

		// PASSWORD CHECK
		String password = (String) authentication.getCredentials();
		if (!encoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("비밀번호를 확인해주세요.");
		}

		// 권한조회
		List<Authority> list = authorutyDao.selectAuthoritiesByUserId(id);
		if (list.size() == 0) {
			// 모든 인증된 사용자는 권한이 있어야 하기 때문에 권한 조회 후 없으면 예외
			throw new UsernameNotFoundException("권한이 없는 사용자 입니다.");
		}

		// SimpleGrantedAuthority - 권한정보를 문자열로 저장.
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		for (Authority au : list) {
			authList.add(new SimpleGrantedAuthority(au.getAuthority()));
		}

		// 인증한 사용자 정보(Principal), 패스워드, 인증된사용자의 권한들 을 넣어 Authentication객체 생성해 리턴
		return new UsernamePasswordAuthenticationToken(user, password, authList);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}

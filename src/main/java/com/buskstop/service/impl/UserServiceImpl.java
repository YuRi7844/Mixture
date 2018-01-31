package com.buskstop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buskstop.dao.AuthorityDao;
import com.buskstop.dao.UserDao;
import com.buskstop.service.UserService;
import com.buskstop.vo.Authority;
import com.buskstop.vo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthorityDao authorDao;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MessageSource messageSource;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	@Transactional
	public void joinMember(User user, Authority authority) {
		userDao.insertUser(user);
		authorDao.insertAuthority(authority);
	}

	@Override
	public User selectMemberById(String id) {
		return userDao.selectUserById(id);
	}

	@Override
	public int updateMember(User user) {
		return userDao.updateUser(user);
	}

	@Override
	@Transactional
	public void dropMember(String id) {
		userDao.dropUpdateUserById(id);
		authorDao.deleteAuthorityById(id);
	}

	@Override
	public String selectMemberByEmail(String email) {
		return userDao.selectUserIdByEmail(email);
	}

	@Override
	@Transactional
	public int findPasswordByEmail(String email) {
		User user = selectMemberById(userDao.selectUserIdByEmail(email));
		if (user == null) {
			return 0;
		} else {
			String newPassword = getRandomPassword(8);
			user.setPassword(encoder.encode(newPassword));
			userDao.updateUser(user);

			MimeMessage message = mailSender.createMimeMessage();
			try {
				MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"utf-8");
				messageHelper.setSubject("Buskstop 비밀번호 찾기 메일입니다.");
				messageHelper.setText(user.getUserId()+" 님의 임시비밀번호는 "+newPassword+" 입니다.");
				messageHelper.setFrom("jisumongo@gmail.com");
				messageHelper.setTo(new InternetAddress(user.getEmail(), user.getUserName()));
				mailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return 1;
		}
	}

	private String getRandomPassword(int length) {
		char[] charaters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuilder sb = new StringBuilder("");
		Random rm = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(charaters[rm.nextInt(charaters.length)]);
		}
		return sb.toString();
	}

	@Override
	public List<User> selectAllUser() {
		return userDao.selectAllUserAndAuthority();
	}

	@Override
	public List<User> dropAndSelectUser(String userId) {
		userDao.dropUpdateUserById(userId);
		return userDao.selectAllUserAndAuthority();
	}

	@Override
	public List<User> searchMemberManagement(String authority, String search) {
		HashMap<String, String> map = new HashMap<>();
		map.put("authority", authority);
		map.put("search", search);
		
		return userDao.selectByAuthorityAndUserId(map);
	}
	
}
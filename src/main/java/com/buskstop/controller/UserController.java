package com.buskstop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.ArtistService;
import com.buskstop.service.PremiumStageService;
import com.buskstop.service.UserService;
import com.buskstop.vo.Artist;
import com.buskstop.vo.Authority;
import com.buskstop.vo.PremiumStage;
import com.buskstop.vo.User;

@Controller
public class UserController {
	
	@Autowired
	MyPageController mpc;
	
	@Autowired
	UserService service;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	PremiumStageService stageService;
	
	/**
	 * 회원가입 컨트롤러
	 * @param user
	 * @return
	 */
	@RequestMapping("/join_member")
	public String joinMember(@ModelAttribute User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		service.joinMember(user, new Authority(user.getUserId(), "ROLE_MEMBER"));
		return "main.tiles";
	}
	
	/************************ 회원수정 전 비밀번호 체크 컨트롤러 ************************/
	
	@RequestMapping("/member/passwordCheck")
	public ModelAndView memPassCheck() {
		ModelAndView mav = mpc.profileInfo();
		
		mav.addObject("category", "user");
		mav.setViewName("myPage/passwordCheck.myTemp");
		
		return mav;
	}
	@RequestMapping("/artist/passwordCheck")
	public ModelAndView artPassCheck() {
		ModelAndView mav = mpc.profileInfo();
		
		mav.addObject("category", "artist");
		mav.setViewName("myPage/passwordCheck.myTemp");
		
		return mav;
	}
	
	@RequestMapping(value="/checkUserPassword", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView checkPassword(String userId, String password,@RequestParam String category) {
		ModelAndView mav = mpc.profileInfo();
		
		// service로 user 객체생성 후 encoder 객체 생성
		User user = service.selectMemberById(userId);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		/*
		 *	category에 따라 switch문을 사용해서 나눈다.
		 *	user/artist/supplier
		 *	비밀번호 체크해서 가는 경로를 다르게 한다.
		 */
		
		switch(category) {
		case "user":
			if(encoder.matches(password, user.getPassword())) {
				mav.addObject("user", user);
				mav.setViewName("myPage/updateMemberView.myTemp");
				return mav;
			}else {
				mav.addObject("errorMsg", "비밀번호를 확인해주세요.");
				mav.addObject("category", "user");
				mav.setViewName("myPage/passwordCheck.myTemp");
				return mav;
			}
		case "artist":
			Artist artist = artistService.readArtistByUserId(user.getUserId());
			if(encoder.matches(password, user.getPassword())) {
				mav.addObject("artist",artist);
				mav.setViewName("myPage/updateArtistView.myTemp");
				return mav;
			}else {
				mav.addObject("errorMsg","비밀번호를 확인해주세요.");
				mav.addObject("category", "artist");
				mav.setViewName("myPage/passwordCheck.myTemp");
				return mav;
			}
		/*case "supplier":
			PremiumStage supplier = stageService.selectSupplierById(user.getUserId());
			if(encoder.matches(password, user.getPassword())) {
				return new ModelAndView("myPage/updateSupplierView.tiles","supplier",supplier);
			}else {
				return new ModelAndView("myPage/passwordCheck.tiles","errorMsg","비밀번호를 확인해주세요.");
			}*/
		default :
			mav.addObject("", null);
			mav.setViewName("/member/myPageMain.do");
			return mav;
		}
	}
	
	/************************ id중복체크 컨트롤러 ************************/
	
	@RequestMapping("/idDuplicatedCheck")
	public @ResponseBody String DuplicatedCheck(String id) {
//		System.out.println(id);
		if(!id.isEmpty()) {
			if(service.selectMemberById(id)==null) {
				return "new";
			} else {
				return "duplicated";
			}
		}else {
			return "empty";
		}
	}
	
	/************************ 회원탈퇴 컨트롤러 ************************/
	
	@RequestMapping("/member/dropUser")
	public String dropUser() {
		/*
		 * 	회원탈퇴 controller
		 * 	id로 user의 회원탈퇴 여부 컬럼 넣는다.
		 * 	session 끊기
		 */
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String id = ((User)authentication.getPrincipal()).getUserId();
		service.dropMember(id);
		context.setAuthentication(null);
		return "redirect:/index.do";
	}
	
	/************************ ID/PASSWORD 찾기 컨트롤러 ************************/
	
	@RequestMapping(value="/findIdByEmail", produces="application/String;charset=utf8")
	public @ResponseBody String findUserIdByEmail(String email) {
		System.out.println(email);
		String userId=service.selectMemberByEmail(email);
		System.out.println(userId);
		if(userId==null) {
			return "등록된 id가 없습니다.";
		}
		return "ID는 "+userId+" 입니다";
	}
	
	// Mailing controller
	@RequestMapping(value="/findPasswordByEmail", produces="application/String;charset=utf8")
	public @ResponseBody String findPasswordByEmail(String id,String email) {
		
		// id로 존재하는 member인지 확인
		if(service.selectMemberById(id)==null) {
			return "idNotFound";
		}
		// 비밀번호 찾기 mailing 하고서 결과값 int 로 return.
		int flagNum = service.findPasswordByEmail(email);
		if(flagNum==0) {
			return "emailNotFound";
		} else {
			return "success";
		}
	}
}

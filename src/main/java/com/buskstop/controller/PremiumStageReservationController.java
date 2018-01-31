package com.buskstop.controller;


import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.buskstop.service.PremiumStageReservationService;
import com.buskstop.service.UserService;
import com.buskstop.vo.PremiumStageOption;
import com.buskstop.vo.PremiumStageOptionList;
import com.buskstop.vo.PremiumStageReservation;
import com.buskstop.vo.User;

@Controller
public class PremiumStageReservationController {

	@Autowired
	MyPageController mpc;
	
	@Autowired
	UserService userService;
	
	@Autowired 
	PremiumStageReservationService service;
	
	/**
	 * 마이페이지 프리미엄 공연장 상세보기에서 옵션 추가페이지로 이동
	 * .do 처리 했지만 단순 페이지 이동임.
	 * @param establishNo
	 * @return
	 */
	@RequestMapping("/producer/goPremiumStageEnterDate")
	public ModelAndView goPremiumStageEnterDate (@RequestParam int establishNo) {
		
		ModelAndView mav = mpc.profileInfo();
		
		List<PremiumStageOption> optionList = service.selectPremiumStageOptionByEstablishNoJoin(establishNo);
		HashMap<String, Object> map = new HashMap<>();
		map.put("establishNo", establishNo);
		map.put("optionList", optionList);
		
		mav.addObject("map", map);
		mav.setViewName("premiumStage/premiumStageEnterDate.myTemp");
		
		return mav;
	}
	
	/**
	 * 대관일 옵션 추가
	 * @param dateList
	 * @param timeList
	 * @param establishNo
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/producer/enterPremiumStageOption")
	public ModelAndView enterPremiumStageOption(@ModelAttribute PremiumStageOptionList optionList, BindingResult r) throws ParseException {
		
		List<PremiumStageOption> list = optionList.getOptionList();
		for(PremiumStageOption option : list) {
			service.createPremiumStageOption(option);
			service.createPremiumStageTime(option);
		}
		return new ModelAndView("redirect:/producer/goPremiumStageEnterDate.do","establishNo", list.get(0).getEstablishNo());
	}
	
	/**
	 * 프리미엄 공연장 대관일 추가할때 날짜 선택하면 그 날짜에 이미 추가되어있는 시간코드를 읽어온다.
	 * jsp에서 추가되어있는 시간코드인지 확인하고 추가되어있다면 아예 뿌려주지 않는다.
	 * @param stageRentalDate
	 * @return
	 */
	@RequestMapping("/producer/readPremiumStageReservationTimeByStageRentalDate")
	public @ResponseBody List<Integer> readPremiumStageReservationTimeByStageRentalDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationDate){
		Date stageRentalDate = reservationDate;
		return service.selectPremiumStageTimeByByStageRentalDate(stageRentalDate);
	}
	
	/**
	 * 옵션 번호로 옵션 삭제
	 * @param optionNo
	 * @param establishNo
	 * @return
	 */
	@RequestMapping("/producer/deletePremiumStageOption")
	public ModelAndView deletePremiumStageOption(@RequestParam int optionNo, @RequestParam int establishNo) {
		service.removePremiumStageOption(optionNo);
		return new ModelAndView("redirect:/producer/goPremiumStageEnterDate.do","establishNo",establishNo);
	}
	
	/**
	 * 옵션의 상태를 바꾼다. 
	 * @param option
	 * @return
	 */
	@RequestMapping("/changePremiumStageState")
	public ModelAndView updatePremiumStageOptionStageState(@ModelAttribute PremiumStageOption option){
		service.updatePremiumStageOptionStageState(option);
		return new ModelAndView("redirect:/producer/myStageDetail.do","establishNum",option.getEstablishNo());
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 사용자가 프리미엄 대관 신청했을때 신청정보를 생성
	 * @param reservation
	 * @return
	 */
	@RequestMapping("/member/createPremiumStageReservation")
	public ModelAndView createPremiumStageReservation(@ModelAttribute PremiumStageReservation reservation) {
		service.createPremiumStageReservation(reservation);
		PremiumStageOption option =  new PremiumStageOption();
		option.setOptionNo(reservation.getOptionNo());
		option.setStageState(1);
		service.updatePremiumStageOptionStageState(option);
		return new ModelAndView("redirect:/member/readPremiumStageReservationByUserId.do", "reservationUserId", reservation.getReservationUserId());
	}
	
	/**
	 * 사용자별 대관신청정보 페이지(마이페이지)
	 * @param reservationUserId
	 * @return
	 */
	@RequestMapping("/member/readPremiumStageReservationByUserId")
	public ModelAndView readPremiumStageReservation(String reservationUserId) {
		List<PremiumStageReservation> list =  service.selectPremiumStageReservationByUserId(reservationUserId);
		for(PremiumStageReservation reservation : list) {
			reservation.setOption(service.selectPremiumStageOptionByOptionNoJoin(reservation.getOptionNo()));
		}
		return new ModelAndView("myPage/myPremiumStageReservationView.tiles", "myReservationList", list);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/producer/readReservationUserDetail")
	public @ResponseBody User readReservationUserDetail(@RequestParam int optionNo){
		PremiumStageReservation reservation = service.selectPremiumStageReservationByOptionNo(optionNo);
		return userService.selectMemberById(reservation.getReservationUserId());
	}
	
}

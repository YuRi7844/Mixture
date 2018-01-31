package com.buskstop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.buskstop.service.HelpCommentService;

@Controller
public class HelpCommentController {

	@Autowired
	HelpCommentService service;
	
	
}

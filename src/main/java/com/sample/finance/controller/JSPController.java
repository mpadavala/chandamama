package com.sample.finance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class JSPController {

	@RequestMapping("/")
	public ModelAndView root(){
        return new ModelAndView("home");
        
	}
	
	@RequestMapping("/index")
	public ModelAndView index(){
        return new ModelAndView("home");
        
	}
	
}

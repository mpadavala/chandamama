package com.sample.finance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ViewController {

	@RequestMapping("/")
	public ModelAndView home(){
        return new ModelAndView("dashboard");
	}
	
	@RequestMapping("/blank")
	public ModelAndView blank(){
        return new ModelAndView("blank");
	}
	
	@RequestMapping("/buttons")
	public ModelAndView buttons(){
        return new ModelAndView("buttons");
	}

	@RequestMapping("/flot")
	public ModelAndView flot(){
        return new ModelAndView("flot");
	}

	@RequestMapping("/forms")
	public ModelAndView forms(){
        return new ModelAndView("forms");
	}

	@RequestMapping("/grid")
	public ModelAndView grid(){
        return new ModelAndView("grid");
	}

	@RequestMapping("/icons")
	public ModelAndView icons(){
        return new ModelAndView("icons");
	}

	@RequestMapping("/index")
	public ModelAndView index(){
        return new ModelAndView("dashboard");
	}

	@RequestMapping("/login")
	public ModelAndView login(){
        return new ModelAndView("login");
	}

	@RequestMapping("/morris")
	public ModelAndView morris(){
        return new ModelAndView("morris");
	}

	@RequestMapping("/notifications")
	public ModelAndView notifications(){
        return new ModelAndView("notifications");
	}

	@RequestMapping("/panels-wells")
	public ModelAndView panelswells(){
        return new ModelAndView("panels-wells");
	}

	@RequestMapping("/tables")
	public ModelAndView tables(){
        return new ModelAndView("tables");
	}

	@RequestMapping("/typography")
	public ModelAndView typography(){
        return new ModelAndView("typography");
	}
}

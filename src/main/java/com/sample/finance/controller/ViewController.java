package com.sample.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping("/")
	public String home(){
        return "dashboard";
	}
	
	@RequestMapping("/blank")
	public String blank(){
        return "blank";
	}
	
	@RequestMapping("/buttons")
	public String buttons(){
        return "buttons";
	}

	@RequestMapping("/flot")
	public String flot(){
        return "flot";
	}

	@RequestMapping("/forms")
	public String forms(){
        return "forms";
	}

	@RequestMapping("/grid")
	public String grid(){
        return "grid";
	}

	@RequestMapping("/icons")
	public String icons(){
        return "icons";
	}

	@RequestMapping("/index")
	public String index(){
        return "dashboard";
	}

	@RequestMapping("/login")
	public String login(){
        return "login";
	}

	@RequestMapping("/morris")
	public String morris(){
        return "morris";
	}

	@RequestMapping("/notifications")
	public String notifications(){
        return "notifications";
	}

	@RequestMapping("/panels-wells")
	public String panelswells(){
        return "panels-wells";
	}

	@RequestMapping("/tables")
	public String tables(){
        return "tables";
	}

	@RequestMapping("/typography")
	public String typography(){
        return "typography";
	}
}

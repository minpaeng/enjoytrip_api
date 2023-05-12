package edu.ssafy.enjoytrip.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class HomeController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/home")
	public String home() {
		return "redirect:/";
	}
	
	@GetMapping("/attraction")
	public String attraction() {
		return "attraction/attraction";
	}
	
	

}

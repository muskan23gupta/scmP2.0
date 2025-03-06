
package com.scm.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {
	@RequestMapping("/home")
	public String home(Model mdl) {
	  System.out.println("Home Page Handler");
	  //sending data to view
	  mdl.addAttribute("name", "Substring Technologies");
	  mdl.addAttribute("YoutubeChannel" ,"Learn Code With Durgesh");
	  mdl.addAttribute("GithubRepository", "https://github.com/learncodewithdurgesh/scm-springboot");
	  return "home";
	}
    
	@RequestMapping("/about")
	public String about(Model mdl){
		mdl.addAttribute("isLogin", true);
		System.out.println("About Page Handler");
		return "about";
	}

	@RequestMapping("/services")
	public String services(){
		System.out.println("Service Page Handler");
		return "services";
	}

	@GetMapping("/login")
	public String login() {
		return new String("login");
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/contact")
	public String contact() {
		return new String("contact");
	}
	
	
	
}


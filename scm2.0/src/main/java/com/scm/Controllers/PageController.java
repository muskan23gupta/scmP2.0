
package com.scm.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}



package com.scm.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class PageController {

    @Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "redirect:/home";
	}
	

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

    //this is login page
	@GetMapping("/login")
	public String login() {
		return new String("login");
	}

	//this is registration controller-viewPage
	@GetMapping("/register")
	public String register(Model mdl) {
		UserForm userForm = new UserForm();
		//userForm.setName("Muskan");
		mdl.addAttribute("userForm", userForm);
		return "register";
	}

	@GetMapping("/contact")
	public String contact() {
		return new String("contact");
	}
	
	//Processing register

	@PostMapping("/do-register")
	public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
		System.out.println("Processing Registration!!");
		System.out.println(userForm);

		if(rBindingResult.hasErrors()){
			return "register";
		}
		//fetch the form data
		//user form
		//validate the form data
	    //save to database
	//	User user = User.builder()
	//	.name(userForm.getName())
	//	.email(userForm.getEmail())
	//	.password(userForm.getPassword())
	//	.about(userForm.getAbout())
	//	.phoneNumber(userForm.getPhoneNumber())
	//	.profilePic("https://images.app.goo.gl/6fdVyqhdbdyEYNL9A")
	//	.build();

	User user = new User();
	user.setName(userForm.getName());
	user.setEmail(userForm.getEmail());
	user.setPassword(userForm.getPassword());
	user.setAbout(userForm.getAbout());
	user.setPhoneNumber(userForm.getPhoneNumber());
	user.setProfilePic("https://images.app.goo.gl/6fdVyqhdbdyEYNL9A");



		User savedUser = userService.saveUser(user);
		System.out.println("User Saved");

		//message to registration successfull
		//add the message(we are using session to add the message)

		Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

		session.setAttribute("message" , message);
		//redirec to the login page
		return "redirect:/register";
	}
}


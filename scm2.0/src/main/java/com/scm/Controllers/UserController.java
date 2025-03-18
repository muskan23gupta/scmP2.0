//it contains the protect url

package com.scm.Controllers;

//import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.scm.config.OAuthAuthenticationSuccessHandler;
import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;


@Controller
public class UserController {

    private final OAuthAuthenticationSuccessHandler OAuthAuthenticationSuccessHandler;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    UserController(OAuthAuthenticationSuccessHandler OAuthAuthenticationSuccessHandler) {
        this.OAuthAuthenticationSuccessHandler = OAuthAuthenticationSuccessHandler;
    }
    
    //user dashboard page
    @RequestMapping(value = "/user/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    //user profile page
    @RequestMapping(value = "/user/profile")
    public String userProfile(Model model, Authentication authentication){
       
        return "user/profile";
    }
    //user add contacts page
    //user edit contact
    //user delete contact
    //user search contact
}

package com.scm.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.scm.Application;
import com.scm.config.OAuthAuthenticationSuccessHandler;
import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {

    private final DaoAuthenticationProvider authenticationProvider;

    private final Application application;

    private final OAuthAuthenticationSuccessHandler OAuthAuthenticationSuccessHandler;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired 
    private UserService userService;

    RootController(OAuthAuthenticationSuccessHandler OAuthAuthenticationSuccessHandler, Application application, DaoAuthenticationProvider authenticationProvider) {
        this.OAuthAuthenticationSuccessHandler = OAuthAuthenticationSuccessHandler;
        this.application = application;
        this.authenticationProvider = authenticationProvider;
    }
    
    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){
        if(authentication == null){
            return ;
        }
        System.out.println("Adding Logged In User Information to the Model");
        String userName = Helper.getEmailOfLoggeduser(authentication);
        logger.info("User Logged In: {} ", userName);

        User user = userService.getUserByEmail(userName);

            System.out.println(user.getName());
            System.out.println(user.getEmail());
            model.addAttribute("loggedInUser", user);
     }
}

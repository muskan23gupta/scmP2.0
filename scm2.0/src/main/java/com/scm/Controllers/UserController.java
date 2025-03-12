package com.scm.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {
    //user dashboard page
    @RequestMapping(value = "/user/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    //user profile page
    @RequestMapping(value = "/user/profile")
    public String userProfile(){
        return "user/profile";
    }
    //user add contacts page
    //user edit contact
    //user delete contact
    //user search contact
}

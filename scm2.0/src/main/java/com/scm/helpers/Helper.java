package com.scm.helpers;

import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.var;

public class Helper {

    public static String getEmailOfLoggeduser(Authentication authentication){
        //if login using email id and password 
       //
        //AuthenticationPrincipal principal = (AuthenticationPrincipal)authentication.getPrincipal();
        if(authentication instanceof OAuth2AuthenticationToken){

           var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
           var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
           var oauth2User = (OAuth2User)authentication.getPrincipal();
           String username = "";

           if(clientId.equalsIgnoreCase("google")){
            System.out.println("Getting email from Google");
           username =  oauth2User.getAttribute("email").toString();
           }
           else if(clientId.equalsIgnoreCase("github")){
            System.out.println("Getting email from github");

            username = (oauth2User.getAttribute("email") != null) ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString() +"@gmail.com";
           }
           return username;
        }

        //if login with google
        //if login with github
        else{
            System.out.println("Getting email from local database");
            return authentication.getName();
        }
        
    }
}

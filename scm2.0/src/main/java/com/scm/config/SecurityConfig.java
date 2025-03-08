//here we are configuring all the security related module 
package com.scm.config;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // we have to bring user detail service object
        daoAuthenticationProvider.setUserDetailsService(null);
        // we have to bring password encoder object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
   //user create and login using java code with in memory service 
   //@Bean
   //public UserDetailsService userDetailsService(){
   //UserDetails user1 = User.withUsername("admin123").password("admin123").roles("admin","user").build();
   //   var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
   //   return inMemoryUserDetailsManager;
   //} 
}

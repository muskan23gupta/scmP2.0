//here we are configuring all the security related module 
package com.scm.config;

import java.io.IOException;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scm.services.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;

    //configuration ofauthenticatin provider for spring security
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // we have to bring user detail service object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // we have to bring password encoder object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
      

        //configuration 

        httpSecurity.authorizeHttpRequests(authorize -> {
          //  authorize.requestMatchers("/home","/register","/services").permitAll();
          authorize.requestMatchers("/user/**").authenticated();
          authorize.anyRequest().permitAll();
        });

        //form default login
        //ifany kind of change required related to form login then we come here
        httpSecurity.formLogin(formLogin -> {

            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");

            formLogin.successForwardUrl("/user/profile");
           // formLogin.failureForwardUrl("/login?error=true");

            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

          //  formLogin.failureHandler(new AuthenticationFailureHandler() {

          //      @Override
          //      public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
          //              AuthenticationException exception) throws IOException, ServletException {
          //          // TODO Auto-generated method stub
          //          throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
          //      }
          //  });

          //  formLogin.successHandler(new AuthenticationSuccessHandler() {

          //      @Override
          //      public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
          //              Authentication authentication) throws IOException, ServletException {
                    // TODO Auto-generated method stub
          //          throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
          //      }
                
          //  });
            });

            httpSecurity.csrf(AbstractHttpConfigurer :: disable);
            httpSecurity.logout(logoutForm -> {
                logoutForm.logoutUrl("/do-logout");
                logoutForm.logoutSuccessUrl("/login?logout=true");
            });

            //oauth configuration with google
            httpSecurity.oauth2Login(oauth -> {
                oauth.loginPage("/login");
                oauth.successHandler(handler);
            });
        return httpSecurity.build();
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

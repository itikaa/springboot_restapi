package com.example.restapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
//import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
 //import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.restapi.service.*;

@Configuration
@EnableWebSecurity
public class springsecurity  {

@Autowired
private UserDetailsServiceImpl userDetailsService;

 @Bean
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
    auth .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
 }
 
@Bean
public PasswordEncoder passwordEncoder(){
 return new BCryptPasswordEncoder();
}

// @Bean 
// public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
//     return(web) -> web.ignoring().antMatchers("");
// }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http  
                 .authorizeRequests()
                .antMatchers("/student/**,/users/** ").authenticated()
                .antMatchers("/admin/** ").hasRole("ADMIN")
                .antMatchers().permitAll()
                
                .and()
                .formLogin();
               http .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();

         return http.build();
    }
    
   
        // @Bean
        // public UserDetailsService userDetailsService() {
        //     return new UserDetailsServiceImpl();
        // }

    }


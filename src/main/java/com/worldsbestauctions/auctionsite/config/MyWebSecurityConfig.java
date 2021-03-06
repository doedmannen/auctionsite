package com.worldsbestauctions.auctionsite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/user").permitAll()
                .antMatchers(HttpMethod.POST,"/api/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers("/").denyAll()
                .and().formLogin()
                .failureUrl("http://localhost:8081/?loginfailure")
                .defaultSuccessUrl("http://localhost:8081/").permitAll()
                .and().logout().logoutSuccessUrl("http://localhost:8081/")
                .deleteCookies("JSESSIONID").permitAll()
                .and().csrf().disable();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(userDetailService.secretUserService.getEncoder());
    }
}

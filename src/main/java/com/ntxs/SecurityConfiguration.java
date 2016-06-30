package com.ntxs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.ntxs.security.AuthenticationFailureHandler;
import com.ntxs.security.AuthenticationSuccessHandler;
import com.ntxs.security.CsrfHeaderFilter;
import com.ntxs.security.RestAccessDeniedHandler;
import com.ntxs.security.UnauthorizedEntryPoint;
import com.ntxs.service.AuthenticationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UnauthorizedEntryPoint unauthorizedEntryPoint;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private RestAccessDeniedHandler restAccessDeniedHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	  web.ignoring().antMatchers("/index.html", "/pages/login.html",
	   "/dist/**", "/", "/error/**","/js/**","/css/**","/bower_components/**");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		  .headers().disable()
		  .csrf().disable()
		  .authorizeRequests()
          .antMatchers("/pages/**").permitAll()
          .anyRequest().authenticated()
          .and()
          .exceptionHandling()
		    .authenticationEntryPoint(unauthorizedEntryPoint)
		    .accessDeniedHandler(restAccessDeniedHandler)
		    .and()
		   .formLogin()
		    .loginProcessingUrl("/authenticate")
		    .successHandler(authenticationSuccessHandler)
		    .failureHandler(authenticationFailureHandler)
		    .usernameParameter("username")
		    .passwordParameter("password")
		    .permitAll()
		    .and()
		   .logout()
		    .logoutUrl("/logout")
		    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
		    .deleteCookies("JSESSIONID")
		    .permitAll()
          .and()
          .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
          .csrf().csrfTokenRepository(csrfTokenRepository());
    }
    
    private CsrfTokenRepository csrfTokenRepository() {
    	  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    	  repository.setHeaderName("X-XSRF-TOKEN");
    	  return repository;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("In Custom authorization");
		 ShaPasswordEncoder encoder = new ShaPasswordEncoder();
         auth.userDetailsService(authenticationService);
    }
}

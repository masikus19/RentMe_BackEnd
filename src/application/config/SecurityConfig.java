package application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import application.jwt.JWTAuthenticationEntryPoint;
import application.jwt.JWTFilter;
import application.jwt.RevokedFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired JWTFilter jwtFilter;
	@Autowired RevokedFilter revoke;
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		
		http.csrf().disable();//allows for spring security 2 running POST requests
		http.httpBasic();//enabling baseAuthentication

		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.cors();
		
		//http.authorizeRequests().antMatchers("/**").permitAll(); // init backdoor
		
		// only ADMIN
		http.authorizeRequests().mvcMatchers(
											API.SECURITY + API.ADD_ACCOUNT,
											API.SECURITY + API.GRANT_ROLE,
											API.SECURITY + API.DEPRIVE_ROLE,
											API.SECURITY + API.GET_ALL_ACCOUNTS,
											API.SECURITY + API.GET_ROLES_BY_LOGIN,
											API.SECURITY + API.REMOVE_ACCOUNT,											
											API.APP + API.ADD_RENTER,
											API.APP + API.ADD_LESSOR,
											API.APP + API.ADMIN).hasRole("ADMIN");
		// ADMIN and MANAGER only
		http.authorizeRequests().mvcMatchers(
											API.SECURITY + API.REMOVE_USER,
											API.SECURITY + API.REMOVE_OWNER,
											API.SECURITY + API.REVOKE_ACCOUNT,
											API.SECURITY + API.ACTIVATE_ACCOUNT,
											API.APP + API.MANAGER).hasAnyRole("ADMIN", "MANAGER");
				
		//OWNER
		http.authorizeRequests().mvcMatchers(API.APP + API.OWNER).hasRole("OWNER");	
		
		//USER
		http.authorizeRequests().mvcMatchers(API.APP + API.RENTER).hasRole("USER");	
				
		//everybody
		http.authorizeRequests().mvcMatchers(
											 API.SECURITY + API.CHANGE_PASSWORD,
											 API.SECURITY + API.ADD_USER,
											 API.SECURITY + API.ADD_OWNER,
											 API.APP + API.EVERYBODY,
											 API.JWT + API.REGISTER).permitAll();
		
		// close everything else for not authenticated
		http.authorizeRequests().anyRequest().authenticated();	
			
		http.addFilterAfter(revoke, BasicAuthenticationFilter.class);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
}





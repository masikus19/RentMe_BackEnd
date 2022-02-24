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
				
		//RENTER
		http.authorizeRequests().mvcMatchers(API.APP + API.RENTER + API.GET_PROFILE_RENTER,
											 API.APP + API.RENTER + API.EDIT_PROFILE_RENTER,
											 API.APP + API.RENTER + API.FIND_PLACE,
											 API.APP + API.RENTER + API.GET_FAVORITES,
											 API.APP + API.RENTER + API.ADD_FAVORITE,
											 API.APP + API.RENTER + API.REMOVE_FAVORITE,
											 API.APP + API.RENTER + API.GET_HISTORY,
											 API.APP + API.RENTER + API.ADD_ANNOUNCEMENT_TO_HISTORY,
											 API.APP + API.RENTER + API.REQUEST_TOUR,
											 API.APP + API.RENTER + API.APPLY_TO_OWNER,
											 API.APP + API.RENTER + API.CONTACT_OWNER).hasRole("USER");	
		
		//OWNER
		http.authorizeRequests().mvcMatchers(API.APP + API.OWNER + API.GET_PROFILE_OWNER,
											 API.APP + API.OWNER + API.EDIT_PROFILE_OWNER,
											 API.APP + API.OWNER + API.ADD_REALTY_OBJECT,
											 API.APP + API.OWNER + API.ADD_ANNOUNCEMENT).hasRole("OWNER");	
				
		
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





package application.jwt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.security.dto.AuthPair;
import application.security.dto.RegisterDto;
import application.security.entities.Account;
import application.security.repositories.AccountMongoRepository;
import application.security.services.BadRequestException;
import application.security.services.ISecurityService;

@RestController
@RequestMapping("/jwt")
public class JWTController {

	@Autowired JWTTokenUtil jwtTokenUtil;
	@Autowired ISecurityService service;
	@Autowired AccountMongoRepository accountRepo;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createToken() throws Exception{
		return makeResponse(jwtTokenUtil.generateToken(SecurityContextHolder.getContext().getAuthentication()));
	}
		
	@PostMapping("/register")
	public ResponseEntity<?> registerAndCreateToken(@RequestBody RegisterDto registerDto){
		String role;
		if(registerDto.getRole().equals("USER"))
			{
				role="USER";
				service.addUser(registerDto.getLogin(), registerDto.getPassword());
			}
		else
		{
			role="OWNER";
			service.addOwner(registerDto.getLogin(), registerDto.getPassword());
		}
		
		return makeResponse(jwtTokenUtil.generateToken(registerDto.getLogin(), registerDto.getPassword(), List.of(role)));		
	}
	
	private ResponseEntity<JWTResponse> makeResponse(String token){
		return ResponseEntity.ok(new JWTResponse(token,
				 								 jwtTokenUtil.getUsernameFromToken(token),
				 								 jwtTokenUtil.getRolesFromToken(token),
				 								 jwtTokenUtil.getExpirationDateFromToken(token)));
	}
}

package application.jwt;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JWTResponse {
	
	private String jwttoken;
	private String login;
	private List<String> roles;
	private Date expiration;

}

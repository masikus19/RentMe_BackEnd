package application.security.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class AccountingParameters {
	
	@Value("${accounting.allowed_roles:ADMIN,MANAGER,OWNER,USER,GUEST}")
	private String[] allowedRoles;
	
	@Value("${accounting.oldPasswordsNum:3}")
	private int oldPasswordNum;		

}

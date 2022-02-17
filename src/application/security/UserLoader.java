package application.security;

import java.util.ArrayList;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import application.security.entities.Account;
import application.security.repositories.AccountMongoRepository;

@Component
public class UserLoader implements UserDetailsService{
	
	@Autowired AccountMongoRepository accountRepo;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Account account = accountRepo.findById(login).orElse(null);
		if(account == null) throw new UsernameNotFoundException("Login not registered");
		Set<String> roles = account.getRoles();
		return new User(login, 
						account.getPassword(),
						AuthorityUtils.createAuthorityList(
									new ArrayList<String>(roles).toArray(new String[roles.size()])));
	}

}

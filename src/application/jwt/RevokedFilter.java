package application.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import application.security.entities.Account;
import application.security.repositories.AccountRepository;

@Component
public class RevokedFilter extends OncePerRequestFilter {

	@Autowired
	AccountRepository accountRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		Authentication context = SecurityContextHolder.getContext().getAuthentication();
		if (context != null) {
			try {
				String login = context.getName();
				Account account = accountRepo.findById(login).orElse(null);
				if (account.isRevoked())
					throw new UsernameNotFoundException("Account revoked");
			} catch (Exception e) {
				request.setAttribute("revoked", e.getMessage());
				SecurityContextHolder.getContext().setAuthentication(null);
			}
		}
		chain.doFilter(request, response);
	}
}

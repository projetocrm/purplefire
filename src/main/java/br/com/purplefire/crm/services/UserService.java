package br.com.purplefire.crm.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.purplefire.crm.security.UserSpringSecurity;

public class UserService {

	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}

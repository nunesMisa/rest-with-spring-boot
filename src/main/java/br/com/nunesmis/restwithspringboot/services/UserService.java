package br.com.nunesmis.restwithspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nunesmis.restwithspringboot.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
	}
}

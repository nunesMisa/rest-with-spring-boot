package br.com.nunesmis.restwithspringboot.controllers.v1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nunesmis.restwithspringboot.data.model.User;
import br.com.nunesmis.restwithspringboot.repository.UserRepository;
import br.com.nunesmis.restwithspringboot.security.AccountCredentialsVO;
import br.com.nunesmis.restwithspringboot.security.jwt.JwtTokenProvider;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserRepository repository;
	
	@PostMapping(value = "/signin", consumes = {"application/json", "application/xml", "application/x-yaml"}, 
	produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> signin(@RequestBody AccountCredentialsVO data) {
		try {
			String username = data.getUsername();
			String password = data.getPassword();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			User user = repository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found."));
			
			String token = tokenProvider.createToken(username, user.getRoles());
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			
			return ResponseEntity.ok().body(model);
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username / password supplied.");
		}
	}
}

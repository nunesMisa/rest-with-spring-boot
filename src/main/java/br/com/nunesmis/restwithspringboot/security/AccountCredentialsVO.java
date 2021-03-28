package br.com.nunesmis.restwithspringboot.security;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @EqualsAndHashCode
public class AccountCredentialsVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;

}

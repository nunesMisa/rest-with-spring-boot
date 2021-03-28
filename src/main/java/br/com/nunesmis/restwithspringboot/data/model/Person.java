package br.com.nunesmis.restwithspringboot.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "ADDRESS", length = 100)
	private String address;
	
	@Column(name = "GENDER", length = 6)
	private String gender;
	
	@Column(name = "ENABLED", nullable = false)
	private Boolean enabled;
	
}

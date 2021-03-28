package br.com.nunesmis.restwithspringboot.data.dto.v2;

import java.io.Serializable;
import java.time.LocalDate;

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
public class PersonDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String gender;
	
	private LocalDate birthDay;
	
}

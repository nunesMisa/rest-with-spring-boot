package br.com.nunesmis.restwithspringboot.converter.custom;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.com.nunesmis.restwithspringboot.data.dto.v2.PersonDTO;
import br.com.nunesmis.restwithspringboot.data.model.Person;

@Service
public class PersonConverter {
	
	public PersonDTO convertEntityToDTO(Person person) {
		return PersonDTO.builder()
				.id(person.getId())
				.firstName(person.getFirstName())
				.lastName(person.getLastName())
				.address(person.getAddress())
				.gender(person.getGender())
				.birthDay(LocalDate.now())
				.build();		
	}
	
	public Person convertDTOToEntity(PersonDTO dto) {
		return Person.builder()
				.id(dto.getId())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.address(dto.getAddress())
				.gender(dto.getGender())
				.build();		
	}
}

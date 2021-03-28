package br.com.nunesmis.restwithspringboot.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.nunesmis.restwithspringboot.data.dto.v1.PersonDTO;
import br.com.nunesmis.restwithspringboot.data.model.Person;

public class MockPerson {

	public Person mockEntity() {
		return mockEntity(0);
	};
	
	public PersonDTO mockDTO() {
		return mockDTO(0);
	}
	
	public List<Person> mockEntityList() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockEntity(i));
		}
		return persons;
	}
	
	public List<PersonDTO> mockDTOEntityList() {
		List<PersonDTO> persons = new ArrayList<PersonDTO>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockDTO(i));
		}
		return persons;
	}
	
	public Person mockEntity(Integer number) {
		return Person.builder()
				.id(Long.valueOf(number))
				.firstName("First Name Test " + number)
				.lastName("Last Name Test " + number)
				.address("Address Test " + number)
				.gender((number % 2) == 0 ? "Male" : "Female")
				.build();
	}
	
	public PersonDTO mockDTO(Integer number) {
		return PersonDTO.builder()
				.key(Long.valueOf(number))
				.firstName("First Name Test " + number)
				.lastName("Last Name Test " + number)
				.address("Address Test " + number)
				.gender((number % 2) == 0 ? "Male" : "Female")
				.build();
	}
}

package br.com.nunesmis.restwithspringboot.converter;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.nunesmis.restwithspringboot.converter.mocks.MockPerson;
import br.com.nunesmis.restwithspringboot.data.dto.v1.PersonDTO;
import br.com.nunesmis.restwithspringboot.data.model.Person;

public class DozerConverterTest {
	
	private MockPerson inputObject;
	
	@BeforeEach
	public void setUp() {
		inputObject = new MockPerson();
	}
	
	@Test
	public void parseEntityToDTOTest() {
		Person input = inputObject.mockEntity();
		PersonDTO output = DozerConverter.parseObject(input, PersonDTO.class);
		Assertions.assertThat(input.getId()).isEqualTo(output.getKey());
		Assertions.assertThat(input.getFirstName()).isEqualTo(output.getFirstName());
		Assertions.assertThat(input.getLastName()).isEqualTo(output.getLastName());
		Assertions.assertThat(input.getAddress()).isEqualTo(output.getAddress());
		Assertions.assertThat(input.getGender()).isEqualTo(output.getGender());
	}

	@Test
	public void parseEntityListToDTOListTest() {
		List<Person> input = inputObject.mockEntityList();
		List<PersonDTO> output = DozerConverter.parseListObjects(input, PersonDTO.class);
		
		for(int i=0; i < input.size(); i++) {
			Assertions.assertThat(input.get(i).getId()).isEqualTo(output.get(i).getKey());
			Assertions.assertThat(input.get(i).getFirstName()).isEqualTo(output.get(i).getFirstName());
			Assertions.assertThat(input.get(i).getLastName()).isEqualTo(output.get(i).getLastName());
			Assertions.assertThat(input.get(i).getAddress()).isEqualTo(output.get(i).getAddress());
			Assertions.assertThat(input.get(i).getGender()).isEqualTo(output.get(i).getGender());			
		}
	}
	
	@Test
	public void parseDTOToEntityTest() {
		PersonDTO input = inputObject.mockDTO();
		Person output = DozerConverter.parseObject(input, Person.class);
		Assertions.assertThat(input.getKey()).isEqualTo(output.getId());
		Assertions.assertThat(input.getFirstName()).isEqualTo(output.getFirstName());
		Assertions.assertThat(input.getLastName()).isEqualTo(output.getLastName());
		Assertions.assertThat(input.getAddress()).isEqualTo(output.getAddress());
		Assertions.assertThat(input.getGender()).isEqualTo(output.getGender());
	}

	@Test
	public void parsDTOListToeEntityListTest() {
		List<PersonDTO> input = inputObject.mockDTOEntityList();
		List<Person> output = DozerConverter.parseListObjects(input, Person.class);
		
		for(int i=0; i < input.size(); i++) {
			Assertions.assertThat(input.get(i).getKey()).isEqualTo(output.get(i).getId());
			Assertions.assertThat(input.get(i).getFirstName()).isEqualTo(output.get(i).getFirstName());
			Assertions.assertThat(input.get(i).getLastName()).isEqualTo(output.get(i).getLastName());
			Assertions.assertThat(input.get(i).getAddress()).isEqualTo(output.get(i).getAddress());
			Assertions.assertThat(input.get(i).getGender()).isEqualTo(output.get(i).getGender());			
		}
	}
}

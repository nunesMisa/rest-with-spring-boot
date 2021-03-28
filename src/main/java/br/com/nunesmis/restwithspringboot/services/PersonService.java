package br.com.nunesmis.restwithspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nunesmis.restwithspringboot.converter.DozerConverter;
import br.com.nunesmis.restwithspringboot.converter.custom.PersonConverter;
import br.com.nunesmis.restwithspringboot.data.dto.v1.PersonDTO;
import br.com.nunesmis.restwithspringboot.data.model.Person;
import br.com.nunesmis.restwithspringboot.exception.ResourceNotFoundException;
import br.com.nunesmis.restwithspringboot.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonConverter converter;
	
	public PersonDTO findById(Long id) {
		return  DozerConverter.parseObject(
					repository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")),
					PersonDTO.class);
	}
	
	public Page<PersonDTO> findAll(Pageable pageableable) {
		return repository.findAll(pageableable).map(this::convertToPersonDTO);
	}
	
	public Page<PersonDTO> findPersonByFirstName(String firstName, Pageable pageableable) {
		return repository.findPersonByFirstName(firstName, pageableable).map(this::convertToPersonDTO);
	}

	public PersonDTO create(PersonDTO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var dto = DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
		return dto;
	}
	
	public PersonDTO update(PersonDTO p) {
		Person entity = repository.findById(p.getKey())
					.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(p.getFirstName());
		entity.setLastName(p.getLastName());
		entity.setAddress(p.getAddress());
		entity.setGender(p.getGender());
		entity.setEnabled(p.getEnabled());
		return DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
	}
	
	public void delete(Long id) {
		Person entity = DozerConverter.parseObject(findById(id), Person.class);;
		repository.delete(entity);
	}
	
	@Transactional
	public PersonDTO disabledPerson(Long id) {
		repository.disabledPerson(id);
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
		
	}
	
	public br.com.nunesmis.restwithspringboot.data.dto.v2.PersonDTO createV2(br.com.nunesmis.restwithspringboot.data.dto.v2.PersonDTO person) {
		var entity = converter.convertDTOToEntity(person);
		var dto = converter.convertEntityToDTO(repository.save(entity));
		return dto;
	}
	
	private PersonDTO convertToPersonDTO(Person entity) {
		return DozerConverter.parseObject(entity, PersonDTO.class);
	}
}

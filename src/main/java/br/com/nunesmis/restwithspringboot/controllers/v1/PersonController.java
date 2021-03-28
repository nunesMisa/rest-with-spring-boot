package br.com.nunesmis.restwithspringboot.controllers.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nunesmis.restwithspringboot.data.dto.v1.PersonDTO;
import br.com.nunesmis.restwithspringboot.services.PersonService;

//@CrossOrigin
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@Autowired
	private PagedResourcesAssembler<PersonDTO> assembler;
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "12") int limit, 
			@RequestParam(value = "direction", defaultValue = "asc") String direction) throws Exception {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageableable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		Page<PersonDTO> dtos =  service.findAll(pageableable);
		dtos.stream().forEach(dto -> {
			try {
				dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(assembler.toModel(dtos), HttpStatus.OK);
	}
	
	@GetMapping(value = "/findPersonByFirstName/{firstName}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value="page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "12") int limit, 
			@RequestParam(value = "direction", defaultValue = "asc") String direction) throws Exception {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageableable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		Page<PersonDTO> dtos =  service.findPersonByFirstName(firstName, pageableable);
		dtos.stream().forEach(dto -> {
			try {
				dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(assembler.toModel(dtos), HttpStatus.OK);
	}

//	@CrossOrigin(origins = {"http://localhost:8080"})
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonDTO findById(@PathVariable("id") Long id) throws Exception {
		PersonDTO dto = service.findById(id);
		dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return dto;
	}
	
//	@CrossOrigin(origins = {"http://localhost:8080", "http://www.erudio.com.br"})
	@PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, 
	produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonDTO create(@RequestBody PersonDTO person) throws Exception {
		PersonDTO dto = service.create(person);
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		return dto;
	}
	
	@PutMapping(consumes = { "application/json", "application/xml", "application/x-yaml"}, 
			produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonDTO update(@RequestBody PersonDTO person) throws Exception {
		PersonDTO dto = service.update(person);
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		return dto;
	}
	
	@PatchMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonDTO disabledPerson(@PathVariable("id") Long id) throws Exception {
		PersonDTO dto = service.disabledPerson(id);
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		return dto;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}

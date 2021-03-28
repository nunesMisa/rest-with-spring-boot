package br.com.nunesmis.restwithspringboot.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nunesmis.restwithspringboot.data.dto.v2.PersonDTO;
import br.com.nunesmis.restwithspringboot.services.PersonService;

@RestController
@RequestMapping("/api/v2/person")
public class PersonControllerV2 {
	
	@Autowired
	private PersonService service;
	
	@PostMapping()
	public PersonDTO create(@RequestBody PersonDTO person) {
		return service.createV2(person);
	}
}

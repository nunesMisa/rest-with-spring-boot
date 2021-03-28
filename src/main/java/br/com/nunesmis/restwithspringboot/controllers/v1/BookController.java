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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nunesmis.restwithspringboot.data.dto.v1.BookDTO;
import br.com.nunesmis.restwithspringboot.services.BookService;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private PagedResourcesAssembler<BookDTO> assembler;
		
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "12") int limit, 
			@RequestParam(value = "direction", defaultValue = "asc") String direction) throws Exception {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageableable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
		
		Page<BookDTO> dtos =  service.findAll(pageableable);
		dtos.stream().forEach(dto -> {
			try {
				dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(assembler.toModel(dtos), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookDTO findById(@PathVariable("id") Long id) throws Exception {
		BookDTO dto = service.findById(id);
		dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return dto;
	}
	
	@PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, 
	produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookDTO create(@RequestBody BookDTO person) throws Exception {
		BookDTO dto = service.create(person);
		dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
		return dto;
	}
	
	@PutMapping(consumes = { "application/json", "application/xml", "application/x-yaml"}, 
			produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookDTO update(@RequestBody BookDTO person) throws Exception {
		BookDTO dto = service.update(person);
		dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
		return dto;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}

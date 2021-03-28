package br.com.nunesmis.restwithspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.nunesmis.restwithspringboot.converter.DozerConverter;
import br.com.nunesmis.restwithspringboot.data.dto.v1.BookDTO;
import br.com.nunesmis.restwithspringboot.data.model.Book;
import br.com.nunesmis.restwithspringboot.exception.ResourceNotFoundException;
import br.com.nunesmis.restwithspringboot.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	public BookDTO findById(Long id) {
		return  DozerConverter.parseObject(
					repository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")),
					BookDTO.class);
	}
	
	public Page<BookDTO> findAll(Pageable pageableable) {
		return repository.findAll(pageableable).map(this::convertToBookDTO);
	}

	public BookDTO create(BookDTO Book) {
		var entity = DozerConverter.parseObject(Book, Book.class);
		var dto = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
		return dto;
	}
	
	public BookDTO update(BookDTO p) {
		Book entity = DozerConverter.parseObject(findById(p.getKey()), Book.class);
		entity.setAuhtor(p.getAuhtor());
		entity.setLauchDate(p.getLauchDate());
		entity.setPrice(p.getPrice());
		entity.setTitle(p.getTitle());
		return DozerConverter.parseObject(repository.save(entity), BookDTO.class);
	}
	
	public void delete(Long id) {
		Book entity = DozerConverter.parseObject(findById(id), Book.class);;
		repository.delete(entity);
	}
	
	private BookDTO convertToBookDTO(Book entity) {
		return DozerConverter.parseObject(entity, BookDTO.class);
	}
}

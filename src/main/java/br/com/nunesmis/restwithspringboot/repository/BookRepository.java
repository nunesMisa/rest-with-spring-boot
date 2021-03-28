package br.com.nunesmis.restwithspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nunesmis.restwithspringboot.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}

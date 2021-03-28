package br.com.nunesmis.restwithspringboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.nunesmis.restwithspringboot.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

	@Modifying
	@Query("UPDATE Person p SET p.enabled = false WHERE p.id = :id")
	void disabledPerson(@Param("id") Long id);
	
	@Query("SELECT p FROM Person p WHERE p.firstName like LOWER(CONCAT('%', :firstName, '%'))")
	Page<Person> findPersonByFirstName(@Param("firstName") String fristName, Pageable pageable);
}

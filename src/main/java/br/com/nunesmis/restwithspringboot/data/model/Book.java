package br.com.nunesmis.restwithspringboot.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "books")
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name = "author", nullable = false, length = 100)
	private String auhtor;
	
	@Column(name = "launch_date", nullable = false, columnDefinition = "DATE")
	private LocalDateTime lauchDate;
	
	@Column(name = "price", columnDefinition = "NUMERIC(65,2)")
	private Double price;
	
	@Column(name = "title", length = 100)
	private String title;
	
}

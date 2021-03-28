package br.com.nunesmis.restwithspringboot.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ExceptionResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private LocalDateTime timestamp;
//	private String status;
//	private String error;
	private String message;
	private String datails;
}

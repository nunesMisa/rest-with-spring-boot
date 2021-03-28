package br.com.nunesmis.restwithspringboot.data.dto.v1;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonPropertyOrder(value = {"fileName", "fileDownloadUri", "fileType", "size"})
public class UploadFileResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fileName;
	
	private String fileDownloadUri;
	
	private String fileType;
	
	private long size;
	
}

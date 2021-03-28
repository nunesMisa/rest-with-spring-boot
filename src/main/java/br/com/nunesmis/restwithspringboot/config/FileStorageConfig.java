package br.com.nunesmis.restwithspringboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @EqualsAndHashCode 
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
	
	private String uploadDir;
}

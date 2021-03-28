package br.com.nunesmis.restwithspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import br.com.nunesmis.restwithspringboot.config.FileStorageConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({
	FileStorageConfig.class
})
@ComponentScan
@OpenAPIDefinition(info = @Info(title = "Teste API", version = "0.0.1", description = "Aplicação de teste e estudos"))
public class RestWithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWithSpringBootApplication.class, args);
		
		/*
		 * BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		 * String result = bCryptPasswordEncoder.encode("admin123");
		 * System.out.println("My hash " + result);
		 */
	}

	
	
}

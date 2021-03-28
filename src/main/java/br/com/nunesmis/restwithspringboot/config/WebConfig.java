package br.com.nunesmis.restwithspringboot.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.nunesmis.restwithspringboot.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private static final MediaType MEDIA_TYPE_YAML = MediaType.valueOf("application/x-yaml"); 

	
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// Via EXTENSION localhost:8080/api/v1/person.xml /* não funcionou */
		/*
		 * configurer .favorParameter(false) .ignoreAcceptHeader(false)
		 * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("xml",
		 * MediaType.APPLICATION_XML) .mediaType("json", MediaType.APPLICATION_JSON);
		 */

		// Via QUERY PARAM localhost:8080/api/v1/person?mediaType=xml
		/*
		 * configurer .favorPathExtension(false) .favorParameter(true)
		 * .parameterName("mediaType") .ignoreAcceptHeader(true)
		 * .useRegisteredExtensionsOnly(false)
		 * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("xml",
		 * MediaType.APPLICATION_XML) .mediaType("json", MediaType.APPLICATION_JSON);
		 */

		// Via HEADER passando ACCEPT = application/xml
		configurer
			.favorParameter(false)
			.ignoreAcceptHeader(false)
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("x-yaml", MEDIA_TYPE_YAML);

	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "PATH", "DELETE", "HEAD", "TRACE", "CONNECT");
	}
}

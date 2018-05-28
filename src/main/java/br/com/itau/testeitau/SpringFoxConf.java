package br.com.itau.testeitau;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConf {
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.itau.testeitau.rest"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Teste Itau REST API", 
						   "APIs utilizadas no teste Itaú.", 
						   "API 0.1", 
						   "#",
						   new Contact("Admin", "www.testeAdmin.com", "admin@company.com"), 
						   "Licença da API", 
						   "#",
						   Collections.emptyList());
	}
	

}
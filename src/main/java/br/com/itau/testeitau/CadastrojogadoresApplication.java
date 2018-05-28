package br.com.itau.testeitau;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@SpringBootApplication
public class CadastrojogadoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastrojogadoresApplication.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
		
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
							.setType(EmbeddedDatabaseType.HSQL) 
							.build();
		return db;
	}
	
}

package com.msandy.loans;

import com.msandy.loans.dtos.ContactDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
@EnableConfigurationProperties(value={ContactDto.class})
@OpenAPIDefinition(info=@Info(title="Sandyz Learning Microservice Rest API documentation:",
		description="Loans Module Microservice",
		version="V1",
		contact=@Contact(name="Sandeep Dahiya",email="insg.sandeep@gmail.com"),
		license=@License(name="Sandeep.com 2.0",url="https://sandy.com")
),
		externalDocs=@ExternalDocumentation(description="ext desc",url="u.com")
)
public class LoanApp
{
	public static void main(String[] args) {
		SpringApplication.run(LoanApp.class, args);
	}
}

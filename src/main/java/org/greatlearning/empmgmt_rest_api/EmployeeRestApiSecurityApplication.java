package org.greatlearning.empmgmt_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class EmployeeRestApiSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRestApiSecurityApplication.class, args);
	}
}

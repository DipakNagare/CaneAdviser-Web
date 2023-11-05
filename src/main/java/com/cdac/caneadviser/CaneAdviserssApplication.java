package com.cdac.caneadviser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.cdac.caneadviser"})

public class CaneAdviserssApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaneAdviserssApplication.class, args);
	}

}

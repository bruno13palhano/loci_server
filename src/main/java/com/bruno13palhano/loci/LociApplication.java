package com.bruno13palhano.loci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bruno13palhano"})
public class LociApplication {

	public static void main(String[] args) {
		SpringApplication.run(LociApplication.class, args);
	}

}

package com.my.PJ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.my.PJ.*"})
@ServletComponentScan
public class PjApplication {

	public static void main(String[] args) {
		SpringApplication.run(PjApplication.class, args);
	}

}

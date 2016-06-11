package com.hackaton.psd2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.hackaton.psd2"})
public class ContahackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContahackApplication.class, args);
	}
}

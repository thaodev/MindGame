package com.mindGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MindGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindGameApplication.class, args);
	}

}

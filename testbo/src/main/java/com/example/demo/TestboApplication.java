package com.example.demo;

//import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestboApplication {

	public static void main(String[] args) {
		// System.setProperty("input", "file://" + new File("D:/code/sboot/testbo/students.csv").getAbsolutePath());
		// System.setProperty("output", "file://" + new File("D:/code/sboot/testbo/studentso.csv").getAbsolutePath());
		SpringApplication.run(TestboApplication.class, args);
	}

}


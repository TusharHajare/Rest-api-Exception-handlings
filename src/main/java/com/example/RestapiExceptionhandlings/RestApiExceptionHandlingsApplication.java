package com.example.RestapiExceptionhandlings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiExceptionHandlingsApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(RestApiExceptionHandlingsApplication.class, args);
		System.out.println("Exception handler...");
	}

}

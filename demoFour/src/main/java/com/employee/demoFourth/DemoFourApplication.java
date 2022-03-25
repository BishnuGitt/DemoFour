package com.employee.demoFourth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.employee.Rest"})
public class DemoFourApplication {

	public static void main(String[] args) throws  ClassNotFoundException{
		SpringApplication.run(DemoFourApplication.class, args);

		// System.out.println("THIS IS enum " + EmployeeEnum.ROLE.getFullFormValue());
	}
	

	
	

}

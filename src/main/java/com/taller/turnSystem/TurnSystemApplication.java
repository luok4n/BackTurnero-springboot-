package com.taller.turnSystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class TurnSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurnSystemApplication.class, args);
	}
        
        @Bean
        public ModelMapper modMapper(){
            return new ModelMapper();
        }
        
}

package com.etec.tcc.sprint_quiz;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.etec.tcc.sprint_quiz.util.ObjectMapperUtils;

@SpringBootApplication
public class SprintQuizApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SprintQuizApplication.class, args);
	}

}

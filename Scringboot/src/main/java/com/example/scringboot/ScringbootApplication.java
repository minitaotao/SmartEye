package com.example.scringboot;

import com.example.scringboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.scringboot.entity.User;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

//@EnableWebMvc
@SpringBootApplication
public class ScringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScringbootApplication.class, args);
	}

}

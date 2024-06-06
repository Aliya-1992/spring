package org.example;


import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
public class SpringEmailDemoApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringEmailDemoApplication.class, args);
        System.out.println("http://localhost:8096/");

    }


}

package ru.nikitapopov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SkillBoxSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkillBoxSpringApplication.class, args);
    }
}
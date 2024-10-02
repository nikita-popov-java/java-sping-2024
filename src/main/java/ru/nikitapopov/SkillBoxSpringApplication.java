package ru.nikitapopov;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SkillBoxSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkillBoxSpringApplication.class, args);
    }
}
package ru.abdramanova.univesity_creation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import ru.abdramanova.univesity_creation.repositories.AssessmentRepository;


@SpringBootApplication
public class UnivesityCreationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnivesityCreationApplication.class, args);

    }

}

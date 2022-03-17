package ru.abdramanova.university_platform;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class UnivesityPlatformApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UnivesityPlatformApplication.class, args);
        context.start();
    }
}

package ru.abdramanova.university_platform;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.abdramanova.university_platform.repositories.TaskRepository;
import ru.abdramanova.university_platform.service.*;

@SpringBootApplication
public class UnivesityPlatformApplication implements CommandLineRunner {

    @Autowired
    private InitService initService;

    private static final Logger LOG = LoggerFactory.getLogger("JCG");

    public static void main(String[] args) {
        SpringApplication.run(UnivesityPlatformApplication.class, args);
    }

    @Override
    public void run(String... strings) {
initService.initDB();

    }
}

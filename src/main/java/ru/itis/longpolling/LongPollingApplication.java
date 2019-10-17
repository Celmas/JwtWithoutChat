package ru.itis.longpolling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "ru.itis.longpolling" })
@EnableJpaRepositories
public class LongPollingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongPollingApplication.class, args);
    }

}

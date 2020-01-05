package org.scd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.scd.repository")
@SpringBootApplication(scanBasePackages="org.scd")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

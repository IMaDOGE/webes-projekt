package com.unideb;

import com.unideb.model.Music;
import com.unideb.repository.MusicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("# test: http://localhost:8080/h2-ui");
        System.out.println("# username: sa");
        System.out.println("# pass:");
        System.out.println("# JDBC URL: jdbc:h2:file:./src/main/database");
    }

    @Profile("music_demo")
    @Bean
    CommandLineRunner initDatabase(MusicRepository repository){
        return args -> {
            repository.save(new Music("test_title1", "test_album", "test_author1", "demo", 210));
        };
    }
}
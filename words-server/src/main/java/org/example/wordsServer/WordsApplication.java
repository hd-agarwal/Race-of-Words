package org.example.wordsServer;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WordsApplication {

    static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(dotenvEntry ->
                System.setProperty(dotenvEntry.getKey(), dotenvEntry.getValue()));
        SpringApplication.run(WordsApplication.class, args);

    }

}

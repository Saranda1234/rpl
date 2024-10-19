package net.enjoy.springboot.premierleague;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PremierLeagueApplication {

    public static void main(String[] args) {
        SpringApplication.run(PremierLeagueApplication.class, args);
    }
}

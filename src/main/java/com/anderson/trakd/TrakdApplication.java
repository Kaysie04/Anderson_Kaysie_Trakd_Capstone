package com.anderson.trakd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
public class TrakdApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrakdApplication.class, args);
    }

}

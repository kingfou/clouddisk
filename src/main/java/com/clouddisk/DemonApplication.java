package com.clouddisk;

/**
 * Created by kingfou on 2018/11/18.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemonApplication.class, args);
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|{}");
    }
}


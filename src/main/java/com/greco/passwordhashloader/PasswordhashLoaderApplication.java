package com.greco.passwordhashloader;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@Slf4j
@EnableAsync
public class PasswordhashLoaderApplication implements CommandLineRunner {

    @Autowired
    private PasswordHashDataLoader passwordHashDataLoader;

    public static void main(String[] args) {

        SpringApplication.run(PasswordhashLoaderApplication.class, args);
    }


    @Override
    public void run(String...args) throws Exception {
        long startTime = System.currentTimeMillis();
        passwordHashDataLoader.readFileandInsertdData();
        log.info("Processed File in " + (System.currentTimeMillis() - startTime) + " ms.");
    }



}

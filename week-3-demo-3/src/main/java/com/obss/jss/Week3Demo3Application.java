package com.obss.jss;

import com.obss.jss.service.ElearningService;
import com.obss.jss.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week3Demo3Application implements CommandLineRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(Week3Demo3Application.class);

    @Autowired
    private ElearningService elearningService;

    @Autowired
    private VideoService videoService;

    public static void main(String[] args) {

        SpringApplication.run(Week3Demo3Application.class, args);


    }

    @Override
    public void run(String... args) throws Exception {

        LOGGER.info("Request url is {}", videoService.getVideoUrl(132L));
        LOGGER.info("Request url is {}", videoService.getVideoUrl(134L));
    }
}

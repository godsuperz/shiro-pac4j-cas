package com.superz.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author zhang.chao
 * @date 2021/1/2
 */
@SpringBootApplication
@EnableScheduling
public class JwtSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringbootApplication.class, args);
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(6);
        scheduler.setThreadNamePrefix("scheduler-");
        return scheduler;
    }
}

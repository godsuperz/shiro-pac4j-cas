package com.superz.jwt.common.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhang.chao
 * @date 2021/1/11
 */
@Component
@Slf4j
public class TestScheduler {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(100);

    @Scheduled(cron = "0/30 * * * * ?")
    public void scheduler1() {
        log.info("=== scheduler1 start: {}", System.currentTimeMillis());
        try {
            Thread.sleep(38000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = 20;
        while (count-- > 0) {
            threadPool.execute(new MyWorker(count));
        }
        log.info("=== scheduler1 end: {}", System.currentTimeMillis());
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void scheduler2() {
        log.info("=== scheduler2 start: {}", System.currentTimeMillis());
        try {
            Thread.sleep(43000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = 10;
        while (count-- > 0) {
            threadPool.execute(new MyWorker(count));
        }
        log.info("=== scheduler2 end: {}", System.currentTimeMillis());
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void scheduler3() {
        log.info("=== scheduler3 start: {}", System.currentTimeMillis());
        try {
            Thread.sleep(61000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = 10;
        while (count-- > 0) {
            threadPool.execute(new MyWorker(count));
        }
        log.info("=== scheduler3 end: {}", System.currentTimeMillis());
    }

    public static class MyWorker implements Runnable {

        private final int count;

        public MyWorker(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            try {
//                log.info("worker: {}", count);
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

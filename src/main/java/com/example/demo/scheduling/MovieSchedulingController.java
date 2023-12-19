package com.example.demo.scheduling;


import com.example.demo.service.MovieSchedulingService;

import lombok.Data;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@RestController
@Data
@EnableScheduling
public class MovieSchedulingController {
    private final
    MovieSchedulingService movieSchedulingService;
    private final
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Scheduled(fixedRate = 10800000)//раз в в 3 часа
    public void getMovie() {
        try {
            scheduler.execute(() -> {
                ResponseEntity
                        .ok()
                        .body(movieSchedulingService.getMovie());
            });
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}

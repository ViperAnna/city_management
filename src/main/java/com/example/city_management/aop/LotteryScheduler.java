package com.example.city_management.aop;

import com.example.city_management.service.Impl.LotteryServiceIml;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotteryScheduler {
    private final LotteryServiceIml lotteryService;

    @Scheduled(fixedRate = 5000)
    public void startLottery() {
        lotteryService.startLottery();
    }
}


package com.youxu.dp.a02_principle.version2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConsoleReporter extends ScheduledReporter{

    private ScheduledExecutorService scheduledExecutorService;

    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage,aggregator,viewer);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                doStatAndReport(startTimeInMillis, endTimeInMillis);
            }
        }, 0L, periodInSeconds, TimeUnit.SECONDS);
    }
}

package com.youxu.dp.a02_principle.version1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestStat {
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;
}

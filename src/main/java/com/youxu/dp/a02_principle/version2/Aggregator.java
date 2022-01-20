package com.youxu.dp.a02_principle.version2;

import java.util.*;
import java.util.stream.Collectors;

public class Aggregator {
    public Map<String, RequestStat> aggregate(
            Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            requestStats.put(apiName, requestStat);
        }
        return requestStats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis/1000));
        return requestStat;
    }

    private double max(List<Double> dataset) {
        return dataset.stream().max(Double::compare).get();
    }
    private double min(List<Double> dataset) {
        return dataset.stream().min(Double::compare).get();
    }
    private double avg(List<Double> dataset) {
        return dataset.stream().collect(Collectors.averagingDouble(Double::doubleValue));
    }
    private double tps(int count, double duration) {
        return count / duration;
    }
    private double percentile999(List<Double> dataset) {
        return dataset.stream().sorted().skip((long) (dataset.size() * 0.999) - 1).findFirst().get();
    }
    private double percentile99(List<Double> dataset) {
        return dataset.stream().sorted().skip((long) (dataset.size() * 0.99) - 1).findFirst().get();
    }
    private double percentile(List<Double> dataset, double ratio) {
        return dataset.stream().sorted().skip((long) (dataset.size() * ratio) - 1).findFirst().get();
    }

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        List<Double> data = Arrays.asList(0.4, 0.5, 0.6, 0.1, 0.8, 0.9, 1.0, 0.2, 0.3, 0.7);
        long a = (long) (data.size() * 0.99);
        System.out.println(aggregator.percentile99(data));
        System.out.println(aggregator.percentile999(data));
        System.out.println(aggregator.percentile(data, 0.5));
    }
}

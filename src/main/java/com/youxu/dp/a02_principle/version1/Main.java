package com.youxu.dp.a02_principle.version1;

/**
 * 数据采集：负责打点采集原始数据，包括记录每次接口请求的响应时间和请求时间。
 * 存储：负责将采集的原始数据保存下来，以便之后做聚合统计。数据的存储方式有很多种，我们暂时只支持 Redis 这一种存储方式，并且，采集与存储两个过程同步执行。
 * 聚合统计：负责将原始数据聚合为统计数据，包括响应时间的最大值、最小值、平均值、99.9 百分位值、99 百分位值，以及接口请求的次数和 tps。
 * 显示：负责将统计数据以某种格式显示到终端，暂时只支持主动推送给命令行和邮件。命令行间隔 n 秒统计显示上 m 秒的数据（比如，间隔 60s 统计上 60s 的数据）。邮件每日统计上日的数据。
 */
public class Main {
    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage);
        consoleReporter.startRepeatedReport(60, 60);
        EmailReporter emailReporter = new EmailReporter(storage);
        emailReporter.addToAddress("wangzheng@xzg.com");
        emailReporter.startDailyReport();
        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

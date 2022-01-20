package com.youxu.dp.a02_principle.version2;

import java.util.Map;

public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);
}

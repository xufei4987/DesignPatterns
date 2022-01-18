package com.youxu.dp.a02_principle;

import java.util.List;
import java.util.Map;

public class RedisMetricsStorage implements MetricsStorage {
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getAllRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}

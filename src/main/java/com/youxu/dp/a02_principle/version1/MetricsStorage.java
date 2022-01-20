package com.youxu.dp.a02_principle.version1;

import java.util.List;
import java.util.Map;

public interface MetricsStorage {
    /**
     * 保存requestInfo
     * @param requestInfo
     */
    void saveRequestInfo(RequestInfo requestInfo);

    /**
     * 查询api一个时间段的请求信息
     * @param apiName
     * @param startTimeInMillis
     * @param endTimeInMillis
     * @return
     */
    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    /**
     * 查询所有api一个时间段的请求信息
     * @param startTimeInMillis
     * @param endTimeInMillis
     * @return
     */
    Map<String,List<RequestInfo>> getAllRequestInfos(long startTimeInMillis, long endTimeInMillis);
}

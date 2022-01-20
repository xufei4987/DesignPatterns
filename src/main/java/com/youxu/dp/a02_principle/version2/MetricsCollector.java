package com.youxu.dp.a02_principle.version2;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.Executors;

/**
 * 外部存储可以通过异步处理进一步优化性能，降低延迟
 */
public class MetricsCollector {
    private MetricsStorage metricsStorage;// 基于接口而非实现编程
    private static final int DEFAULT_STORAGE_THREAD_POOL_SIZE = 20;
    private EventBus eventBus;
    // 依赖注入
    public MetricsCollector(MetricsStorage metricsStorage) {
        this(metricsStorage,DEFAULT_STORAGE_THREAD_POOL_SIZE);
    }
    public MetricsCollector(MetricsStorage metricsStorage, int threadNumToSaveData) {
        this.metricsStorage = metricsStorage;
        this.eventBus = new AsyncEventBus(Executors.newFixedThreadPool(threadNumToSaveData));
        this.eventBus.register(new EventListener());
    }

    // 用一个函数代替了最小原型中的两个函数
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        eventBus.post(requestInfo);
    }

    public class EventListener{
        @Subscribe
        public void saveRequestInfo(RequestInfo requestInfo) {
            metricsStorage.saveRequestInfo(requestInfo);
        }
    }
}

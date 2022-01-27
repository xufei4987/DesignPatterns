package com.youxu.dp.a04_pattern.eventbus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncEventBus extends AbstractEventBus{
    public static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 1 << 3;

    public AsyncEventBus() {
        super(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));
    }

    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}

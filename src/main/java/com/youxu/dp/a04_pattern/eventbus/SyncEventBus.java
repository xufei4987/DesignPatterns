package com.youxu.dp.a04_pattern.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

public class SyncEventBus extends AbstractEventBus{

    public SyncEventBus() {
        super(MoreExecutors.directExecutor());
    }

}

package com.youxu.dp.a04_pattern.eventbus;

import java.util.List;
import java.util.concurrent.Executor;

public abstract class AbstractEventBus {
    protected Executor executor;
    protected ObserverRegistry observerRegistry = new ObserverRegistry();
    protected AbstractEventBus(Executor executor){
        this.executor = executor;
    }
    public void register(Object object) {
        observerRegistry.register(object);
    }
    public void post(Object event) {
        List<ObserverAction> observerActions = observerRegistry.getMatchedObserverActions(event);
        observerActions.forEach(action ->
                executor.execute(() -> action.execute(event))
        );
    }
}

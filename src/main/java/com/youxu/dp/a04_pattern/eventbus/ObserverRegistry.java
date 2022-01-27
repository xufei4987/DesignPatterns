package com.youxu.dp.a04_pattern.eventbus;

import com.google.common.base.Preconditions;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class ObserverRegistry {

    private ConcurrentHashMap<Class<?>, CopyOnWriteArraySet<ObserverAction>> registry = new ConcurrentHashMap<>();

    public void register(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = findAllObserverActions(observer);
        observerActions.forEach((eventType,eventActions) -> {
            CopyOnWriteArraySet<ObserverAction> registeredEventActions = registry.get(eventType);
            if (registeredEventActions == null){
                registry.putIfAbsent(eventType,new CopyOnWriteArraySet<>());
                registeredEventActions = registry.get(eventType);
            }
            registeredEventActions.addAll(eventActions);
        });
    }

    public List<ObserverAction> getMatchedObserverActions(Object event) {
        List<ObserverAction> matchedObservers = new ArrayList<>();
        Class<?> postedEventType = event.getClass();
        registry.forEach((eventType,eventActions) -> {
            if (postedEventType.isAssignableFrom(eventType)) {
                matchedObservers.addAll(eventActions);
            }
        });
        return matchedObservers;
    }

    private Map<Class<?>, Collection<ObserverAction>> findAllObserverActions(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = new HashMap<>();
        Class<?> clazz = observer.getClass();
        for (Method method : getAnnotatedMethods(clazz)){
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];
            observerActions.putIfAbsent(eventType,new CopyOnWriteArraySet<>());
            observerActions.get(eventType).add(new ObserverAction(observer,method));
        }
        return observerActions;
    }

    private List<Method> getAnnotatedMethods(Class<?> clazz) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Preconditions.checkArgument(parameterTypes.length == 1,
                        "Method %s has @Subscribe annotation but has %s parameters."
                                + "Subscriber methods must have exactly 1 parameter.",
                        method, parameterTypes.length);
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}

package com.youxu.dp.a05_practice.mybatis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invocation {
    private final Object target;
    private final Method method;
    private final Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        System.out.println(String.format("class:%s, method:%s",target.getClass().getSimpleName(),method.getName()));
        return method.invoke(target, args);
    }
}

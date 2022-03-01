package com.youxu.dp.a05_practice.mybatis;

public interface Interceptor {
    Object intercept(Invocation invocation) throws Throwable;
    Object plugin(Object target);
}

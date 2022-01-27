package com.youxu.dp.a04_pattern.interceptorchain;

public interface Interceptor {
    //拦截操作，返回true继续后续的处理
    boolean preHandle();
    //所有拦截器preHandle返回true后的操作
    void postHandle();
    //已执行的拦截器需要执行的操作
    void afterCompletion();
}

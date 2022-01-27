package com.youxu.dp.a04_pattern.interceptorchain;

public class AInterceptor implements Interceptor{
    @Override
    public boolean preHandle() {
        System.out.println("AInterceptor...preHandle...success");
        return true;
    }

    @Override
    public void postHandle() {
        System.out.println("AInterceptor...postHandle");
    }

    @Override
    public void afterCompletion() {
        System.out.println("AInterceptor...afterCompletion");
    }
}

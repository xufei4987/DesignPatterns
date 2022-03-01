package com.youxu.dp.a04_pattern.interceptorchain;

public class BInterceptor implements Interceptor{
    @Override
    public boolean preHandle() {
        System.out.println("BInterceptor...preHandle...fail");
        return true;
    }

    @Override
    public void postHandle() {
        System.out.println("BInterceptor...postHandle");
    }

    @Override
    public void afterCompletion() {
        System.out.println("BInterceptor...afterCompletion");
    }
}

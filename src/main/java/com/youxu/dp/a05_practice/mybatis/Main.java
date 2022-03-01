package com.youxu.dp.a05_practice.mybatis;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Interceptor> interceptors = Arrays.asList(new AInterceptor(), new BInterceptor());
        Object handler = new Handler();
        for (Interceptor interceptor : interceptors) {
            handler = interceptor.plugin(handler);
        }
        ((IHandler)handler).handle();
    }
}

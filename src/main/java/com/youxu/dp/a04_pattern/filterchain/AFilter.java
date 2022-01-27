package com.youxu.dp.a04_pattern.filterchain;

public class AFilter implements Filter{
    @Override
    public void doFilter(FilterChain filterChain) {
        System.out.println("before AFilter");
        filterChain.doFilter();
        System.out.println("after AFilter");
    }
}

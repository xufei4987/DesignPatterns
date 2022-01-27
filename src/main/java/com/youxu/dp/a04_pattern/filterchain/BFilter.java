package com.youxu.dp.a04_pattern.filterchain;

public class BFilter implements Filter{
    @Override
    public void doFilter(FilterChain filterChain) {
        System.out.println("before BFilter");
        filterChain.doFilter();
        System.out.println("after BFilter");
    }
}

package com.youxu.dp.a04_pattern.filterchain;

public class FilterChain {
    private int pos = 0; //当前执行到了哪个filter
    private int n = 0; //filter的个数
    private Filter[] filters;

    public FilterChain(int capacity) {
        this.filters = new Filter[capacity];
    }

    public void addFilter(Filter filter){
        for (Filter flt : filters) {
            if (flt == filter){
                return;
            }
        }
        if (n == filters.length){
            Filter[] newFilters = new Filter[n << 1];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
        }
        filters[n++] = filter;
    }

    public void doFilter(){
        if (pos < n){
            Filter filter = filters[pos++];
            filter.doFilter(this);
        } else {
            // todo filter都处理完毕后的相关操作
            System.out.println("filter都处理完毕后的相关操作");
        }
    }

    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain(8);
        filterChain.addFilter(new AFilter());
        filterChain.addFilter(new BFilter());
        filterChain.doFilter();
    }
}

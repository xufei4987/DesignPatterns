package com.youxu.dp.a04_pattern.interceptorchain;

public class InterceptorChain {
    private int pos = 0; //当前执行到了哪个Interceptor
    private int n = 0; //Interceptor的个数
    private Interceptor[] interceptors;

    public InterceptorChain(int capacity) {
        this.interceptors = new Interceptor[capacity];
    }

    public void addInterceptor(Interceptor interceptor) {
        for (Interceptor i : interceptors) {
            if (i == interceptor) {
                return;
            }
        }
        if (n == interceptors.length) {
            Interceptor[] newInterceptors = new Interceptor[n << 1];
            System.arraycopy(interceptors, 0, newInterceptors, 0, n);
            interceptors = newInterceptors;
        }
        interceptors[n++] = interceptor;
    }

    public void doIntercept() {
        boolean success = applyPreHandle();
        if (!success) {
            return;
        }
        // todo 通过拦截器链后执行的操作
        System.out.println("通过拦截器链后执行的操作");
        applyPostHandle();
        triggerAfterCompletion();
    }

    private boolean applyPreHandle() {
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (!interceptors[i].preHandle()) {
                pos++;
                triggerAfterCompletion();
                return false;
            }
            pos++;
        }
        return true;
    }

    private void applyPostHandle() {
        if (n == 0) {
            return;
        }
        for (int i = n - 1; i >= 0; i--) {
            interceptors[i].afterCompletion();
        }
    }

    private void triggerAfterCompletion() {
        if (n == 0) {
            return;
        }
        for (int i = pos - 1; i >= 0; i--) {
            interceptors[i].afterCompletion();
        }
    }

    public static void main(String[] args) {
        InterceptorChain interceptorChain = new InterceptorChain(8);
        interceptorChain.addInterceptor(new AInterceptor());
        interceptorChain.addInterceptor(new BInterceptor());
        interceptorChain.doIntercept();
    }
}

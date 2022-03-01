package com.youxu.dp.a05_practice.mybatis;
@Intercepts(
        {
        @Signature(type = IHandler.class, method = "handle", args = {})
        }
)
public class BInterceptor implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("pre BInterceptor");
        Object proceed = invocation.proceed();
        System.out.println("post BInterceptor");
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
}

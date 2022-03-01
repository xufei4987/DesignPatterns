package com.youxu.dp.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcTest {
    public static void main(String[] args) {
        UserService userService = (UserService)Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, new UserServiceRpc());
        userService.getUserInfo();
    }
}

interface UserService{
    void getUserInfo();
}

class UserServiceRpc implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        System.out.println(method.getDeclaringClass().getName());
        System.out.println("rpc call");
        return null;
    }

}

package com.youxu.dp.a04_pattern.eventbus;

import java.lang.annotation.*;

/**
 * 用于标明观察者中的哪个函数可以接收消息
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Subscribe {
}

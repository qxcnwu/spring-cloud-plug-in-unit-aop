package com.qxc.interfaces;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 0:10
 * @Version 1.0
 * @PACKAGE com.qxc.interfaces
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Log {
    String name();

    String info() default "";

    boolean requireTime() default true;
}

package com.qxc.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 15:27
 * @Version 1.0
 * @PACKAGE com.qxc.interfaces
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Requires {
    Require[] value();
}

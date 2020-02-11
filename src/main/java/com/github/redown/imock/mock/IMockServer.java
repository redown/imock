package com.github.redown.imock.mock;

import java.lang.annotation.*;

/**
 * @author redown
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface IMockServer {
    IMockServerType type();
    int port();
    Class<?> hander();
    String host() default "0.0.0.0";
    String name() default "MockServer";
    String path() default "/";
}

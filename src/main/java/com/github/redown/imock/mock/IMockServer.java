package com.github.redown.imock.mock;

import java.lang.annotation.*;

/**
 * @author redown
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface IMockServer {
    String host() default "0.0.0.0";
    int port();
    Class<?> hander();
    String name() default "ExampleServer";
}

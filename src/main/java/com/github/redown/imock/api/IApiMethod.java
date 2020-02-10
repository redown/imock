package com.github.redown.imock.api;

import java.lang.annotation.*;

/**
 * @author redown
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@Documented
public @interface IApiMethod {
    String name();
    String id() default "";
    String alias() default "";
}

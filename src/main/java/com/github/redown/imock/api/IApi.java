package com.github.redown.imock.api;

import java.lang.annotation.*;

/**
 * @author redown
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface IApi {
    String name();
    String alias() default "";
    String type() default "json";
}

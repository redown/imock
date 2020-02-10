package com.github.redown.imock.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author redown
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface IApiField {
    String name();
    Class<?> type() default String.class;
    int[] length() default {-1, -1};
    String alias() default "";
    String rule() default "";
    int[] range() default {-1, -1};
    String value() default "";
}

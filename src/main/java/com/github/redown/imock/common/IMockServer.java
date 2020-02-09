package com.github.redown.imock.common;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;

import java.lang.annotation.*;

/**
 * @Author redown
 * @Date 2020/2/9 0009 10:11
 * @Description
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IMockServer {
    String host() default "0.0.0.0";
    int port();
    Class<?> hander();
    String name() default "ExampleServer";
}

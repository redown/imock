package com.github.redown.imock.common;

import com.github.redown.imock.example.ExampleServer;
import io.netty.channel.ChannelHandler;

/**
 * @Author redown
 * @Date 2020/2/9 0009 10:13
 * @Description
 */
public class MockServer {

    public static IMockServerContext run(Class<ExampleServer> serverClass, String[] args) {
        IMockServer iMockServer = serverClass.getDeclaredAnnotation(IMockServer.class);
        if (iMockServer == null) {
            return null;
        }
        String host = iMockServer.host();
        int port = iMockServer.port();
        Class handlerClass = iMockServer.hander();
        if (!ChannelHandler.class.isAssignableFrom(handlerClass)) {
            return null;
        }
        String name = serverClass.getName();
        IMockServerContext context = null;
        try {
            context = new MockServerContext(host, port, (ChannelHandler) handlerClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return context;
    }
}

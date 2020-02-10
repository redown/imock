package com.github.redown.imock.mock;

import com.github.redown.imock.utils.ApiUtils;
import io.netty.channel.ChannelHandler;

/**
 * @author redown
 */
public class MockServer {

    public static MockServerContext run(Class<?> serverClass, String[] args) {
        System.out.println("--------------------------------------");
        System.out.println("------  IMock   ----------------------");
        System.out.println("--------------------------------------");
        MockServerContext context = initServer(serverClass);
        initApi(context, serverClass);
        context.start();
        return context;
    }

    private static void initApi(IMockServerContext context, Class<?> serverClass) {
        context.addApi(ApiUtils.apiScan(serverClass));
    }

    private static MockServerContext initServer(Class<?> serverClass) {
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
        return new MockServerContext(host, port, handlerClass);
    }
}

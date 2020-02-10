package com.github.redown.imock.mock;

import com.github.redown.imock.api.Api;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author redown
 */
public class MockServerContext implements IMockServerContext {
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGtoup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;
    private String host;
    private int port;
    ChannelHandler serverHandler;
    List<Api> apis = new ArrayList<>();

    public MockServerContext(String host, int port, Class<ChannelHandler> handlerClass) {
        this.host = host;
        this.port = port;
        try {
            this.serverHandler = handlerClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        bossGroup = new NioEventLoopGroup();
        workerGtoup = new NioEventLoopGroup();
        try {
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup)
                    .localAddress(new InetSocketAddress(host, port))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGtoup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void addApi(Api api) {
        this.apis.add(api);
    }

    @Override
    public void addApi(List<Api> apis) {
        this.apis.addAll(apis);
    }
}
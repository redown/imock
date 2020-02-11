package com.github.redown.imock.mock;

import com.github.redown.imock.api.Api;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

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
    private String serverName;
    private IMockServerType serverType;
    private String host;
    private int port;
    ChannelHandler serverHandler;
    List<Api> apis = new ArrayList<>();

    public MockServerContext(String serverName, IMockServerType serverType, String host, int port, Class<ChannelHandler> handlerClass) {
        this.serverName = serverName;
        this.serverType = serverType;
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
                    .childHandler(channelInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            channelFuture = serverBootstrap.bind(port).sync();
            System.out.println(serverName + " start up on port : " + port);
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

    private ChannelInitializer channelInitializer() {
        ChannelInitializer initializer = null;
        switch (serverType) {
            case TCP: initializer = tcpChannelInitializer(serverHandler); break;
            case HTTP: initializer = httpServerInitializer(serverHandler); break;
            case SOAP: initializer = null ; break;
            case ISO8583: initializer = null ; break;
            default: initializer = null; break;
        }
        return initializer;
    }

    private ChannelInitializer tcpChannelInitializer(ChannelHandler serverHandler) {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(serverHandler);
            }
        };
    }

    private ChannelInitializer httpServerInitializer(ChannelHandler serverHandler) {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline()
                        .addLast(new HttpServerCodec())
                        .addLast(HttpObjectAggregator.class.getName(), new HttpObjectAggregator(512*1024))
                        .addLast(serverHandler);
            }
        };
    }
}
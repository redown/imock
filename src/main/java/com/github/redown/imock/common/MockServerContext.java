package com.github.redown.imock.common;

import com.github.redown.imock.service.discard.DiscardServerHander;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author redown
 * @Date 2020/2/9 0009 10:25
 * @Description
 */
public class MockServerContext implements IMockServerContext {
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGtoup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;
    private String host;
    private int port;
    ChannelHandler serverHandler;

    public MockServerContext(String host, int port, final ChannelHandler serverHandler) {
        this.host = host;
        this.port = port;
        this.serverHandler = serverHandler;
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
}
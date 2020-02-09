package com.github.redown.imock.service.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Author redown
 * @Date 2020/2/9 0009 11:31
 * @Description
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext context, Object message) {
        ByteBuf byteBuf = (ByteBuf) message;
        System.out.println("Maseage Echo: "+byteBuf.toString(CharsetUtil.UTF_8));
        context.writeAndFlush(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}

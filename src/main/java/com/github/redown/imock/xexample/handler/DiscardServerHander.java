package com.github.redown.imock.xexample.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author redown
 */
@ChannelHandler.Sharable
public class DiscardServerHander extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext context, Object message) {
        ByteBuf byteBuf = (ByteBuf) message;
        System.out.println("Maseage Discard: "+byteBuf.toString(CharsetUtil.UTF_8));
        byteBuf.release();
//        ReferenceCountUtil.release(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}

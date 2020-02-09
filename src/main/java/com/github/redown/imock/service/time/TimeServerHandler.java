package com.github.redown.imock.service.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * @Author redown
 * @Date 2020/2/9 0009 11:34
 * @Description
 */
@ChannelHandler.Sharable
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(final ChannelHandlerContext context) {
        ByteBuf byteBuf = context.alloc().buffer(4);
        int t = (int) (System.currentTimeMillis());
        System.out.println(t+":"+new Date(t));
        byteBuf.writeInt(t);
        final ChannelFuture future = context.writeAndFlush(byteBuf);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                assert future == channelFuture;
                context.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}

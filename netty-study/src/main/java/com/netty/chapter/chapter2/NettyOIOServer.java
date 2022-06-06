package com.netty.chapter.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.nio.ByteBuffer;

/**
 * @ClassName NettyOIOServer
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/6/10 17:18
 * @Version 1.0
 **/
public class NettyOIOServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        EventLoopGroup eventLoopGroup=new OioEventLoopGroup();
        EventLoopGroup eventExecutors=new EpollEventLoopGroup();
        try {
            ByteBuf byteBuffer=Unpooled.copiedBuffer("hi".getBytes());
            serverBootstrap.group(eventLoopGroup)
                    .channel(OioServerSocketChannel.class)
                    .localAddress(21)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.writeAndFlush(byteBuffer.duplicate())
                                    .addListener(ChannelFutureListener.CLOSE);
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}

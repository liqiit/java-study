package com.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class RegisterCenter {
    private int port = 8080;

    public RegisterCenter(int port) {
        this.port = port;
    }

    public void start() {
        //主线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //工作线程池
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                    }
                });

    }

    public static void main(String[] args) {
        new RegisterCenter(8080).start();
    }
}

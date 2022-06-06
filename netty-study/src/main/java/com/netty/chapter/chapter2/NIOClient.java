package com.netty.chapter.chapter2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NIOClient
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/6/10 15:04
 * @Version 1.0
 **/
public class NIOClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(21));
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array()));

            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

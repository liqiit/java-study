package com.netty.chapter.chapter2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @ClassName NIOServer
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/6/10 15:04
 * @Version 1.0
 **/
public class NIOServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(21));
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            ServerSocket socket = serverSocketChannel.socket();
            final ByteBuffer byteBuffer = ByteBuffer.wrap("hi".getBytes());
            for (; ; ) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    selectionKeys.remove(selectionKey);
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        try {
                            SocketChannel socketChannel = server.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, byteBuffer.duplicate());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (selectionKey.isWritable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        try {
                            while (buffer.hasRemaining()){
                                if(client.write(buffer)==0){
                                    break;
                                }
                            }
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
}

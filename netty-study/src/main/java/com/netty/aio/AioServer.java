package com.netty.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class AioServer {
    private ExecutorService executorService;
    private AsynchronousServerSocketChannel serverChannel;

    public AioServer(int port) {
        init(port);
    }

    private void init(int port) {
        try {
            serverChannel = AsynchronousServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(port));
            serverChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AioServer>() {
                @Override
                public void completed(final AsynchronousSocketChannel clientChannel, AioServer attachment) {
                    attachment.serverChannel.accept(attachment, this);

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    clientChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            System.out.println("receive client data length:" + attachment.capacity());
                            attachment.flip();
                            System.out.println("from client:" + new String(attachment.array(), StandardCharsets.UTF_8));
                            doWrite(clientChannel);


                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {

                        }
                    });
                }

                @Override
                public void failed(Throwable exc, AioServer attachment) {
                    exc.printStackTrace();
                }
            });
            TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doWrite(AsynchronousSocketChannel clientChannel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        byteBuffer.put(line.getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        clientChannel.write(byteBuffer);
    }

    public static void main(String[] args) {
        new AioServer(6666);
    }
}

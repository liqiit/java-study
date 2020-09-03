package com.netty.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Title: MultiThreadEchoHandler
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/2
 */
public class MultiThreadEchoHandler implements Runnable {
    static final int RECEIVING = 0, SENDING = 1;
    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    int state = RECEIVING;
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public MultiThreadEchoHandler(SocketChannel socketChannel, Selector selector) throws IOException {
        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);
        this.selectionKey = socketChannel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        executorService.execute(new AsyncTask());
    }

    public void asyncRun() throws IOException {
        if (state == RECEIVING) {
            int length;
            while ((length=socketChannel.read(byteBuffer))>0){
                System.out.println("收到："+new String(byteBuffer.array(),0,length));
            }
            byteBuffer.flip();
            selectionKey.interestOps(SelectionKey.OP_WRITE);
            state=SENDING;
        } else if (state == SENDING) {
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
            selectionKey.interestOps(SelectionKey.OP_READ);
            state = RECEIVING;
        }
    }

    class AsyncTask implements Runnable {
        @Override
        public void run() {
            try {
                MultiThreadEchoHandler.this.asyncRun();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}

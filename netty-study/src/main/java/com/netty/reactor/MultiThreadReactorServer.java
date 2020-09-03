package com.netty.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Title: MultiThreadReactorServer
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/2
 */
public class MultiThreadReactorServer {
    ServerSocketChannel serverSocketChannel;
    AtomicInteger next = new AtomicInteger(0);
    Selector[] selectors = new Selector[2];
    SubReactor[] subReactors;

    public MultiThreadReactorServer() throws IOException {
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 2929));
        SelectionKey selectionKey = serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptHandler());
        subReactors = new SubReactor[2];
        subReactors[0] = new SubReactor(selectors[0]);
        subReactors[1] = new SubReactor(selectors[1]);

    }

    public static void main(String[] args) {
        try {
            new MultiThreadReactorServer().startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startService() {
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();

    }

    class SubReactor implements Runnable {
        final Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        dispatch(selectionKey);
                        iterator.remove();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void dispatch(SelectionKey selectionKey) {
            Runnable runnable = (Runnable) selectionKey.attachment();
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    class AcceptHandler implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    new MultiThreadEchoHandler(socketChannel, selectors[next.get()]);
                }
                if (next.incrementAndGet() == selectors.length) {
                    next.set(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.netty.reactor;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Title: ReactorServer
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/1
 */
public class ReactorServer implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public ReactorServer() throws IOException {
        serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        selector=Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Iterator<SelectionKey> iterable=selector.selectedKeys().iterator();
                while (iterable.hasNext()){
                    SelectionKey selectionKey=iterable.next();
                    Runnable handler=(Runnable)selectionKey.attachment();
                    if(handler!=null){
                        handler.run();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new Thread(new ReactorServer()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class AcceptorHandler implements Runnable{
        @Override
        public void run() {
            try {
                SocketChannel socketChannel= serverSocketChannel.accept();
                if(socketChannel!=null){
                    new EchoHandler(socketChannel,selector);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class EchoHandler implements Runnable{
        final SocketChannel socketChannel;
        final SelectionKey sk;

        public EchoHandler(SocketChannel socketChannel, Selector selector) throws IOException {
            this.socketChannel = socketChannel;
            socketChannel.configureBlocking(false);
            this.sk = socketChannel.register(selector,0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        }

        @Override
        public void run() {

        }
    }
}



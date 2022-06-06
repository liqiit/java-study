package com.netty.chapter.chapter2;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName OIOServer
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/6/10 14:15
 * @Version 1.0
 **/
public class OIOServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(21);
            for (; ; ) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    OutputStream outputStream;
                    try {
                        outputStream = socket.getOutputStream();
                        outputStream.write("hello world".getBytes(CharsetUtil.UTF_8));
                        outputStream.flush();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

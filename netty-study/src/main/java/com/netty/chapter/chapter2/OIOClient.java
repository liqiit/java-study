package com.netty.chapter.chapter2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @ClassName OIOClient
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/6/10 14:21
 * @Version 1.0
 **/
public class OIOClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 21);
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int read ;
            while ((read = inputStream.read(bytes)) != -1) {
                String request = new String(bytes, 0, read, "utf-8");
                System.out.println(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}

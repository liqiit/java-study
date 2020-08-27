package com.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel {
    public static void main(String[] args) {
        try {
            String str = "hello,鸟";
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/liqi/logs/simgos/a.txt");
            FileChannel fileChannel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(str.getBytes());

            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
}

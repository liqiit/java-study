package com.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileCopy {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/1.txt");
            FileChannel channel1 = fileInputStream.getChannel();

            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/2.txt");
            FileChannel channel2 = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            while (true) {
                byteBuffer.clear();
                int read = channel1.read(byteBuffer);
                if (read == -1) {
                    break;
                }
                byteBuffer.flip();
                channel2.write(byteBuffer);
            }
            channel1.close();
            channel2.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

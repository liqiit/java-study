package com.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileRead {
    public static void main(String[] args) {
        try {
            File file = new File("/Users/liqi/logs/simgos/a.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

            fileChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array()));
            fileChannel.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

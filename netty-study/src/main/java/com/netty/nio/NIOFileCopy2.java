package com.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOFileCopy2 {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("");

            FileOutputStream fileOutputStream = new FileOutputStream("");

            FileChannel fileInputStreamChannel = fileInputStream.getChannel();

            FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

            fileOutputStreamChannel.transferFrom(fileInputStreamChannel, 0, fileInputStreamChannel.size());

            fileInputStreamChannel.close();
            fileOutputStreamChannel.close();
            fileInputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

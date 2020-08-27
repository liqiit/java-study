package com.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterTest {
    public static void main(String[] args) {
        try {
            File file = new File("/Users/liqi/logs/simgos/a.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer intBuffer = ByteBuffer.allocate(1);
            ByteBuffer intBuffer2 = ByteBuffer.allocate(3);
            ByteBuffer[] buffers = {intBuffer, intBuffer2};
            fileChannel.read(buffers);
            System.out.println(new String(intBuffer.array()));
            System.out.println(new String(intBuffer2.array()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

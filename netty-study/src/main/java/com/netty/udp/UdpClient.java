package com.netty.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Title: UdpClient
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/1
 */
public class UdpClient {
    public static void main(String[] args) {
        try {
            DatagramChannel channel=DatagramChannel.open();

            channel.configureBlocking(false);
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

            Scanner scanner=new Scanner(System.in);
            while (scanner.hasNext()){
                String input=scanner.next();
                byteBuffer.put(input.getBytes(StandardCharsets.UTF_8));
                byteBuffer.flip();
                channel.send(byteBuffer,new InetSocketAddress("127.0.0.1",8080));
                byteBuffer.clear();
            }
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package com.netty.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * Title: UdpServer
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/1
 */
public class UdpServer {
    public static void main(String[] args) {
        try {
            DatagramChannel datagramChannel=DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(new InetSocketAddress("127.0.0.1",8080));
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

            Selector selector=Selector.open();
            datagramChannel.register(selector,SelectionKey.OP_READ);

            while (selector.select()>0){
              Iterator<SelectionKey>iterator= selector.selectedKeys().iterator();
              while (iterator.hasNext()){
                  SelectionKey selectionKey=iterator.next();
                  if (selectionKey.isReadable()){
                      try {
                          datagramChannel.receive(byteBuffer);
                          byteBuffer.flip();
                          System.out.println(new String(byteBuffer.array(),0,byteBuffer.limit()));
                          byteBuffer.clear();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
              }
              iterator.remove();
            }
            selector.close();
            datagramChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

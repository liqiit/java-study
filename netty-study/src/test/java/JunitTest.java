import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.epoll.EpollSocketChannel;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;


/**
 * @ClassName JunitTest
 * @Deacription TODO
 * @Author liqi
 * @Date 2021/6/8 16:53
 * @Version 1.0
 **/

public class JunitTest {
    @Test
    public void test() {
        Channel channel = new EpollSocketChannel();
        ChannelFuture channelFuture = channel.connect(new InetSocketAddress("127.0.0.1", 21));
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                ByteBuf byteBuf = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                future.channel().writeAndFlush(byteBuf);
            }else{
                future.cause();
            }
        });

    }
}

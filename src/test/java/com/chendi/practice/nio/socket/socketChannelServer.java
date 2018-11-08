package com.chendi.practice.nio.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author chendi
 * @Date 2018/8/12.
 * @descript socketChannel 服務端聯係
 */
public class socketChannelServer {

    @Test
    public void socketServer() {
        try {

            //創建socket服務端
            ServerSocketChannel sschannel = ServerSocketChannel.open();
            sschannel.bind(new InetSocketAddress(9999));
            sschannel.configureBlocking(false);
            //注册器
            Selector selector = Selector.open();
            sschannel.register(selector, SelectionKey.OP_ACCEPT);
            //
            while (true) {
                if (selector.select(6000) < 1) {
                    System.out.println("暂无客户端准备就绪");
                    continue;
                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    //判断监听器中对应通道的状态
                    if (key.isAcceptable()) {//判断准备就绪
                        SocketChannel socketChannel = sschannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                         while ((socketChannel.read(buf)>0)){
                             buf.flip();
                             System.out.println("客户端传输的内容:" + new String(buf.array(), 0, buf.limit()));
                             buf.clear();
                         }
                        socketChannel.register(selector, SelectionKey.OP_WRITE);
                    } else if (key.isValid() && key.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        buf.put("已接收到你方发生的数据".getBytes());
                        buf.flip();
                        socketChannel.write(buf);
                        // 设置为下一次读取或是写入做准备
                        socketChannel.register(selector,SelectionKey.OP_READ |SelectionKey.OP_WRITE);
                    }
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

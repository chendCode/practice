package com.chendi.practice.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Author chendi
 * @Date 2018/8/12.
 * @descript udp 通道练习
 */
public class DatagramChannelTest {

    public static void main(String [] args) {
        try {
            //获取通道
            DatagramChannel datagramChannel = DatagramChannel.open();
            //将通道设置为非阻塞模式
            datagramChannel.configureBlocking(false);
            Scanner in =new Scanner(System.in);
            //绑定服务端
            datagramChannel.connect(new InetSocketAddress("127.0.0.1", 8998));
            //设置缓存器
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (in.hasNext()){
                buf.put(in.next().getBytes());
                buf.flip();
                datagramChannel.write(buf);
                buf.flip();
                buf.clear();

                datagramChannel.receive(buf);
                buf.flip();
                System.out.println("接收到服務端相應數據"+new String(buf.array(),0,buf.limit()));
                buf.clear();
            }
            datagramChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void server() {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(new InetSocketAddress(8998));
            //获取注册器
            Selector selector = Selector.open();
            //将通道注册到注册器并监听相应的事件
            datagramChannel.register(selector, SelectionKey.OP_READ);
            //监听注册器上已经准备好的通道
            while (selector.select() > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                if (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isReadable()) {
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        SocketAddress address = datagramChannel.receive(buf);
                        buf.flip();
                        System.out.println(new String(buf.array(),0,buf.limit()));
                        buf.clear();
                        buf.put("已經接收到相應數據".getBytes());
                        buf.flip();
                        datagramChannel.send(buf,address);
                        buf.clear();
                    }
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

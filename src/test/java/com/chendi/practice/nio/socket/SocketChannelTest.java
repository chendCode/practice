package com.chendi.practice.nio.socket;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @Author chendi
 * @Date 2018/8/12.
 * @descript socketChannel 練習
 */
public class SocketChannelTest {

    public  static  void main(String [] args){

        try {
            //获取套接字客户端
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
            socketChannel.configureBlocking(false);
            //选择器
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
            Scanner scanner = new Scanner(System.in);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (scanner.hasNext()){
                    buffer.put(scanner.next().getBytes());
                    buffer.flip();
                    socketChannel.write(buffer);
                    buffer.clear();
            }
            socketChannel.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

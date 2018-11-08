package com.chendi.practice.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.util.Scanner;

/**
 * @Author chendi
 * @Date 2018/8/12.
 * @descript 管道数据传输
 */
public class PipeNioTest {

    /**
     * 管道数据来源
     */
    public class PipeSourcesThread implements Runnable {
        private Pipe pipe;

        public PipeSourcesThread(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            try {
                Pipe.SinkChannel sinkChannel = pipe.sink();
                System.out.println("-----------------------------------------");
                ByteBuffer buf = ByteBuffer.allocate(1024);
                buf.put("管道通讯测试".getBytes());
                buf.flip();
                sinkChannel.write(buf);
                buf.clear();
                sinkChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 管道接收线程
     */
    public class PipeTargetThread implements Runnable {
        private Pipe pipe;

        public PipeTargetThread(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            try {
                ByteBuffer buf = ByteBuffer.allocate(1024);
                    Pipe.SourceChannel source = pipe.source();
                    source.read(buf);
                    buf.flip();
                    System.out.println(new String(buf.array()));
                    buf.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void Test() {
        try {
            Pipe pipe = Pipe.open();
            Scanner in = new Scanner(System.in);
            PipeSourcesThread sourcesThead = new PipeSourcesThread(pipe);
            PipeTargetThread targetThead = new PipeTargetThread(pipe);
            Thread t1 = new Thread(sourcesThead);
            Thread t2 = new Thread(targetThead);
            t1.start();
            t2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String [] args) {
        try {

            Pipe pipe = Pipe.open();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            ByteBuffer buf1 = ByteBuffer.allocate(1024);

            Scanner in = new Scanner(System.in);
            Pipe.SinkChannel sinkChannel = pipe.sink();
            Pipe.SourceChannel sourceChannel = pipe.source();
            while (in.hasNext()) {
                String temp = in.next();
                buf.put(temp.getBytes());
                buf.flip();
                sinkChannel.write(buf);
                sourceChannel.read(buf1);
                buf1.flip();
                System.out.println(new String(buf1.array(), 0, buf1.limit()));
                buf1.clear();
                buf.clear();
            }
            sinkChannel.close();
            sourceChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






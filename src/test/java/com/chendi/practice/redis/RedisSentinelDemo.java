package com.chendi.practice.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author chendi
 * @Date 2018/10/12.
 * @descript redis 哨兵模式主从集群demo
 */
public class RedisSentinelDemo {

    /**
     * sentinel 集群池
     */
    private JedisSentinelPool sentinelPool;
    /**
     * redis 客户端
     */
    private Jedis client;

    /**
     * 集群主节点名称
     */
    private static String MASTER_NAME = "mymaster";
    /**
     * 集群服务密码
     */
    private static String PASSWORD = "andyChen";


    @Before
    public void ResdisSentinelPrepare() {
        if (sentinelPool == null) {
            Set<String> sentinelHosts = new HashSet<>();
            sentinelHosts.add("172.16.5.135:26379");
            sentinelHosts.add("172.16.5.135:26380");
            sentinelHosts.add("172.16.5.135:26381");
            //设置连接池配置
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMaxIdle(10); //空闲时存活的连接数
            poolConfig.setMaxTotal(500);//连接池最大的连接数
            poolConfig.setMaxWaitMillis(1500); //最大等待时长 毫秒
            sentinelPool = new JedisSentinelPool(MASTER_NAME, sentinelHosts, poolConfig, PASSWORD);
        }
    }


    @Test
    public void RedisSentinelTest() {
        try {
            getClient();
            if (client.isConnected()) {
                if (exits("yzx")) {
                    del("yzx");
                }
                setNxStr("yzx", "我是你爹爹");
            } else {
                System.out.println("redis 连接失败");
            }

            System.out.println(client.get("yzx"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
                sentinelPool.close();
            }
        }
    }

    /**
     * 获取客户端
     *
     * @return
     */
    public synchronized Jedis getClient() {
        if (client != null)
            return client;
        client = sentinelPool.getResource();
        return client;
    }

    /**
     * @date: 2018/10/15 15:27
     * @Author chendi
     * @description 判断key是否已经存在
     * @param:
     * @return:
     **/

    public boolean exits(String key) {
        getClient();
        return client.exists(key);
    }

    /**
     * 删除相应的key
     *
     * @param key
     * @return
     */
    public boolean del(String key) {
        getClient();
        Long res = client.del(key);
        return res > 0L ? true : false;
    }

    /**
     * 获取key对应的值
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
        getClient();
        return client.get(key);
    }

    /***
     * 设置key 对应的值value
     * @param key
     * @param value
     * @return
     */
    public boolean setStr(String key, String value) {
        try {
            getClient();
            client.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置key值 以及生命周期
     *
     * @param key
     * @param value
     * @param timeOut 生命周期(秒)
     * @return
     */
    public boolean setStr(String key, String value, Long timeOut) {
        try {
            getClient();
            client.set(key, value);
            if (timeOut > 0L) {
                client.expire(key, timeOut.intValue());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 不存在相应的key就进行设值 如果存在则忽悠
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setNxStr(String key, String value) {
        try {
            getClient();
            client.setnx(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取散列类型数据 某一字段的值
     *
     * @param key   键
     * @param field 字段
     * @return
     */
    public String hGet(String key, String field) {
        getClient();
        return client.hget(key, field);
    }

    /**
     * 返回hash数据类型key对应所有字段以及其值
     *
     * @param key
     * @return
     */
    public Map hGetAll(String key) {
        getClient();
        return client.hgetAll(key);
    }

    /**
     * 散列类型设值
     *
     * @param key   key
     * @param field 字段
     * @param value 值
     * @return
     */
    public boolean hSet(String key, String field, String value) {
        try {
            getClient();
            client.hset(key, field, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 散列类型设值
     *
     * @param key   key
     * @param field 字段
     * @param value 值
     * @return
     */
    public boolean hSet(String key, String field, String value, Long timeOut) {
        try {
            getClient();
            long res = client.hset(key, field, value);
            if (timeOut > 0L && res > 0L) {
                client.expire(key, timeOut.intValue());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据指定的 key field 获取对应的字段值
     *
     * @param key
     * @param filed
     * @return
     */
    public List<String> hMget(String key, String... filed) {
        getClient();
        return client.hmget(key, filed);
    }

    /**
     * 批量插入散列数据类型
     *
     * @param key
     * @param data    散列数据类型 field-value 键值对
     * @param timeOut
     * @return
     */
    public boolean hMset(String key, Map data, Long timeOut) {
        try {
            getClient();
            String res = client.hmset(key, data);
            System.out.println(res);
            if (timeOut > 0L) {
                client.expire(key, timeOut.intValue());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * key不存在则设值 存在则忽律
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public boolean hSetNx(String key, String field, String value) {
        try {
            getClient();
            long res = client.hsetnx(key, field, value);
            System.out.println(res);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 散列删除某一字段值
     * @param key
     * @param field
     * @return
     */
    public boolean hdel(String key, String field) {
        getClient();
        Long res = client.hdel(key, field);
        return  res.compareTo(0L)>0?true:false;
    }

    /**
     * 散列删除多个字段值
     * @param key
     * @param fields
     * @return
     */    public boolean hdel(String key, String... fields) {
        getClient();
        Long res = client.hdel(key, fields);
        return  res.compareTo(0L)>0?true:false;
    }

    /**
     *@date: 2018/10/15 16:47
     * @Author chendi
     * @description 从队列左边入队列
     * @param: key 键值 value 入队列的数据
     * @return:
     **/

    public  boolean lPush(String key,String value){
         getClient();
        Long res = client.lpush(key,value);
        return  res.compareTo(0L)>0?true:false;
    }

    /**
     *@date: 2018/10/15 16:47
     * @Author chendi
     * @description 从队列右边入队列
     * @param: key 键值 value 入队列的数据
     * @return:
     **/

    public  boolean rPush(String key,String value){
        getClient();
        Long res = client.rpush(key,value);
        return  res.compareTo(0L)>0?true:false;
    }

    /**
     * 右出队列
     * @param key
     * @return
     */
    public String rPop(String key){
        getClient();
        return client.rpop(key);
    }

    /**
     * 左出队列
     * @param key
     * @return
     */
    public String lPop(String key){
        getClient();
        return client.lpop(key);
    }


    /**
     * brpop blpop 阻塞出队列
     * 当没有数据时阻塞出队列，当等待时间超过timeout时返回
     * @param key
     * @param timeOut
     * @return
     */
    public String rPop(String key,Long timeOut){
        getClient();
        client.brpop(timeOut.intValue(),key);
        return client.rpop(key);
    }

}

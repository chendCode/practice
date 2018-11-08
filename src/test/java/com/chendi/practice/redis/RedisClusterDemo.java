package com.chendi.practice.redis;

import org.junit.Before;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author chendi
 * @Date 2018/8/21.
 * @descript Redis 集群
 */
public class RedisClusterDemo {

    JedisPoolConfig config;

    JedisCluster cluster;

    @Before
    public JedisPoolConfig getRedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(500);
        config.setMaxIdle(10);
        return config;
    }


    @Before
    public JedisCluster getJedisCluster(){
        HostAndPort server1 = new HostAndPort("127.0.0.1",6379);
        HostAndPort server2 = new HostAndPort("127.0.0.1",6379);
        Set<HostAndPort> set = new HashSet<>();
        set.add(server1);
        set.add(server2);
         cluster = new JedisCluster(set,6000,config);
        return cluster;
    }


    public void getStr(){
         String val = cluster.get("123");
    }

    public void setStr(){
         cluster.set("123","sb");
    }


}

package com.jt.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

//表示redis配置类
@Configuration //xml
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
	
	@Value("${redis.nodes}")
	private String redisNodes; //node1,node2,node3
	
	@Bean	//<bean id="" class="">
	public ShardedJedis shardedJedis() {
		List<JedisShardInfo> shards = new ArrayList<>();
		//ip:端口,ip:端口
		String[] nodes = redisNodes.split(",");
		for (String node : nodes) {
			String host = node.split(":")[0];
			int port = Integer.parseInt(node.split(":")[1]);
			JedisShardInfo info = 
					new JedisShardInfo(host, port);
			shards.add(info);
		}
	
		return new ShardedJedis(shards);
	}
}	
	
	
	
	/*
	 * @Value("${jedis.host}") private String host;
	 * 
	 * @Value("${jedis.port}") private Integer port;
	 * 
	 * @Bean public Jedis jedis() {
	 * 
	 * return new Jedis(host, port); }
	 */


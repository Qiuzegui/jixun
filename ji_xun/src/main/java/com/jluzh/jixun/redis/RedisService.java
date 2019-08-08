package com.jluzh.jixun.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

	@Autowired
	JedisPool jedisPool;
	
	
	public <T> T get(KeyPrefix prefix,String key,Class<T> calzz){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			if(key==null||key.length()<=0){
				return null;
			}
			String reallyKey=prefix.getprefix()+key;
			String value = jedis.get(reallyKey);
			T t = StringToBean(value,calzz);
			return t;
		}finally{
			returnToPool(jedis);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private <T>T StringToBean(String value,Class<T>clazz) {
		if(value==null||value.length()<=0){
			return null;
		}
		if(clazz == int.class||clazz == Integer.class){
			return (T)Integer.valueOf(value);
		}if(clazz == String.class){
			return (T)value;
		}if(clazz == long.class||clazz == Long.class){
			return (T)Long.valueOf(value);
		}else{
			return JSON.toJavaObject(JSON.parseObject(value), clazz);
		}
		
	}

	private void returnToPool(Jedis jedis) {
		// TODO Auto-generated method stub
		if(jedis != null){
			jedis.close();
		}
	}

	public <T> boolean set(KeyPrefix prefix,String key,T value){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			String t = BeanToString(value);
			if(key==null||key.length()<=0){
				return false;
			}
			String reallyKey = prefix.getprefix();
			jedis.set(reallyKey+key, t);
			return true;
		}finally{
			returnToPool(jedis);
		}
	}
	
	private <T>String BeanToString(T value) {
		if(value==null){
			return null;
		}
		
		Class<?>clazz = value.getClass();
		if(clazz == int.class||clazz == Integer.class){
			return ""+value;
		}if(clazz == String.class){
			return (String)value;
		}if(clazz == long.class||clazz == Long.class){
			return ""+value;
		}else{
			return JSON.toJSONString(value);
		}
	}

	
	
}

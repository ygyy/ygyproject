package com.glodon.java1.seckill.redis;

import com.alibaba.fastjson.JSON;
import com.glodon.java1.seckill.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

@Service
public class RedisService {
	
	@Autowired
    JedisPool jedisPool;
	
	/**
	 * 获取当个对象
	 * */
	public <T> T get(KeyPrefix prefix, Integer id,  Class<T> clazz) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			 //生成真正的key
			 String realKey  = prefix.getPrefix(id);
			 String  str = jedis.get(realKey);
			 T t =  stringToBean(str, clazz);
			 return t;
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	/**
	 * 设置对象
	 * */
	public <T> boolean set(KeyPrefix prefix, Integer id,  T value) {
		 Jedis jedis = null;
		 try {
			 System.out.println(jedisPool);
			 jedis =  jedisPool.getResource();
			 String str = beanToString(value);
			 if(str == null || str.length() <= 0) {
				 return false;
			 }
			//生成真正的key
			 String realKey  = prefix.getPrefix(id) ;
			jedis.set(realKey,str);
			 return true;
		 }finally {
			  returnToPool(jedis);
		 }
	}



	public  Long  addOneBuyMessage(String userId, String productId, Order order){
		Jedis jedis = null;
		try {
			jedis =  jedisPool.getResource();
			String message =beanToString(order);
			return	jedis.hset(userId,productId,message);
		}finally {
			returnToPool(jedis);
		}
	}

	/**
	 *获得一秒杀信息
	 * @param mapName 存放秒杀信息的map名词
	 * @param uid  userid
	 * @return  返回为order对象
	 */
	public  Order  getOneBuyMessage(String mapName, String uid){
		Jedis jedis = null;
		try {
			jedis =  jedisPool.getResource();
			if(jedis.hexists(mapName,uid)){
				String message =jedis.hget(mapName,uid);
				return	stringToBean(message,Order.class);
			}else {
				return null;
			}
		}finally {
			returnToPool(jedis);
		}
	}

	/**
	 *
	 * @param mapName  map集合名称
	 * @param t  obj 类型
	 * @param <T>
	 * @return  查询所有缓存商品 ：getAllobj(nampName,Product.class);
	 */
	public <T> List<T>  getAllobj(String mapName,Class t){
		Jedis jedis = null;
		try {
			Class<T> tClass= (Class<T>) t.getClass();
			jedis =  jedisPool.getResource();
			List<T> list=new ArrayList<>();
			List<String> message =jedis.hvals(mapName);
			ListIterator<String> listIterator=message.listIterator();
			while (listIterator.hasNext()){
				String temp=listIterator.next();
				T objList= (T) stringToBean(temp,t);
				list.add(objList);
			}
			return list;
		}finally {
			returnToPool(jedis);
		}
	}
	/**
	 * 判断key是否存在
	 * */
	public <T> boolean exists(KeyPrefix prefix,Integer id) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getPrefix(id);
			return  jedis.exists(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	/**
	 * 删除
	 * */
	public boolean delete(KeyPrefix prefix, Integer id) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			String realKey  = prefix.getPrefix(id) ;
			long ret =  jedis.del(realKey);
			return ret > 0;
		 }finally {
			  returnToPool(jedis);
		 }
	}

	/**
	 *
	 * @param mapName mapName hash集合名字
	 * @param map  集合
	 * @return String
	 */

	public String  addHashMap(String mapName, HashMap<String,String> map){
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			//插入map
		 return 	jedis.hmset(mapName,map);
		}finally {
			returnToPool(jedis);
		}
	}

	/**
	 *插入一个map元素
	 * @param mapName mapName hash集合名字
	 * @param key  商品id
	 * @param val
	 * @return
	 */
	//插入一个map元素
	public Long  addMapElement(String mapName, String key,String val){
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			//插入map
			return 	jedis.hset(mapName,key,val);

		}finally {
			returnToPool(jedis);
		}
	}

	/**
	 *递增库存
	 * @param mapName hash集合名字
	 * @param key 商品id
	 * @param <T>
	 * @return
	 */
	public <T> Long hincr(String mapName, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			return  jedis.hincrBy(mapName,key,1);
		 }finally {
			  returnToPool(jedis);
		 }
	}


	/**
	 *
	 * @param mapName hash集合名字
	 * @param key  商品id
	 * @param <T> 减少后的库存数 当库存为0时执行失败返回-1
	 * @return
	 */
	public <T> Long hdecr(String mapName, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();

			 if(getPVal(mapName,key)>0) {
				 return jedis.hincrBy(mapName,key,-1);
			 }else {
			 	return -1L;
			 }

		 }finally {
			  returnToPool(jedis);
		 }
	}

	/**
	 *
	 * @param mapName 获得库存
	 * @param key  商品 id
	 * @return  null表示redis中没有该商品
	 */
	public Integer getPVal(String mapName,String key){
		Jedis jedis = null;
		try {
			jedis =  jedisPool.getResource();
			if (jedis.hexists(mapName, key)) {

				return Integer.parseInt( jedis.hget(mapName,key));
			}else {
				return null;
			}

		}finally {
			returnToPool(jedis);
		}
	}


	/**
	 *获得某map集合中的一个对象  例如查询缓存中的某一个商品信息 gethval(mapName,productId,obj) 注意商品库存请以 getPVal()为准
	 * @param mapName
	 * @param key
	 * @return
	 */
	public <T> T getHVal(String mapName,String key,T obj){
		Jedis jedis = null;
		try {
			jedis =  jedisPool.getResource();
			if (jedis.hexists(mapName, key)) {
			String s=jedis.hget(mapName,key);
			return (T) stringToBean(s,obj.getClass());
			}else {
				return null;
			}

		}finally {
			returnToPool(jedis);
		}
	}
	
//	public boolean delete(KeyPrefix prefix) {
//		if(prefix == null) {
//			return false;
//		}
//		List<String> keys = scanKeys(prefix.getPrefix());
//		if(keys==null || keys.size() <= 0) {
//			return true;
//		}
//		Jedis jedis = null;
//		try {
//			jedis = jedisPool.getResource();
//			jedis.del(keys.toArray(new String[0]));
//			return true;
//		} catch (final Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			if(jedis != null) {
//				jedis.close();
//			}
//		}
//	}
	
//	public List<String> scanKeys(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = jedisPool.getResource();
//			List<String> keys = new ArrayList<String>();
//			String cursor = "0";
//			ScanParams sp = new ScanParams();
//			sp.match("*"+key+"*");
//			sp.count(100);
//			do{
//				ScanResult<String> ret = jedis.scan(cursor, sp);
//				List<String> result = ret.getResult();
//				if(result!=null && result.size() > 0){
//					keys.addAll(result);
//				}
//				//再处理cursor
//				cursor = ret.getStringCursor();
//			}while(!cursor.equals("0"));
//			return keys;
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//		}
//	}
//
	public static <T> String beanToString(T value) {
		if(value == null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if(clazz == int.class || clazz == Integer.class) {
			 return ""+value;
		}else if(clazz == String.class) {
			 return (String)value;
		}else if(clazz == long.class || clazz == Long.class) {
			return ""+value;
		}else {
			return JSON.toJSONString(value);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T stringToBean(String str, Class<T> clazz) {
		if(str == null || str.length() <= 0 || clazz == null) {
			 return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			 return (T)Integer.valueOf(str);
		}else if(clazz == String.class) {
			 return (T)str;
		}else if(clazz == long.class || clazz == Long.class) {
			return  (T)Long.valueOf(str);
		}else {
			return JSON.toJavaObject(JSON.parseObject(str), clazz);
		}
	}

	private void returnToPool(Jedis jedis) {
		 if(jedis != null) {
			 jedis.close();
		 }
	}

	/**
	 * 差入一商品
	 * @param mapName
	 * @param key
	 * @param t 泛型的对象
	 * @param <T>
	 * @return
	 */

	public <T> Long setOneObj(String mapName,String key,T t){
			String objs=beanToString(t);
			return addMapElement(mapName,key,objs);
	}
}

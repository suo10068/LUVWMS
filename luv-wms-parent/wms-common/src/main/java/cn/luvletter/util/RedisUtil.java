package cn.luvletter.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Zephyr Ji
 * @ Description: Redis工具类
 * @ Date 2018/3/5
 */
@Component
@SuppressWarnings("unchecked")
public class RedisUtil {
    @Resource
    private RedisTemplate redisTemplate;
    /**
     * @Description: 存储map
     * @Date: 9:29 2018/3/28
     */
    public void hmSet(Object name, Object key, Object value){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(name, key, value);
    }
    /**
     * @Description: 根据map name 和 key 获取value
     * @Date: 9:29 2018/3/28
     */
    public Object hGet(Object name, Object key){
        if(name == null || key == null){
            return null;
        }
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(name, key);
    }
    /**
     * @Description: 根据map name 判断 这个map中是否有这个key
     * @Date: 9:30 2018/3/28
     */
    public boolean hContainKey(Object name, Object key){
        HashOperations ops = redisTemplate.opsForHash();
        return ops.hasKey(name, key);
    }
    /**
     * @Description: 移除name的map中为key的值
     * @Date: 9:30 2018/3/28
     */
    public long hRemoveByKey(Object name, Object key){
        HashOperations ops = redisTemplate.opsForHash();
        return ops.delete(name, key);
    }
    /**
     * @Description: 存储str，有效时间单位为秒
     * @Date: 15:22 2018/4/16
     */
    public void strSet(Object key, Object value, long validTime){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key,value,validTime, TimeUnit.SECONDS);
    }
    /**
     * @Description: 根据key拿到str
     * @Date: 15:28 2018/4/16
     */
    public Object strGet(Object key){
        ValueOperations ops = redisTemplate.opsForValue();
        return ops.get(key);
    }
}

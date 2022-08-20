package com.zzx.controller;

import com.zzx.vo.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@RestController
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 添加数据到redis
     * 需要注入RedisTemplate
     * RedisTemplate的两种泛型【要么不写】
     * RedisTemplate<String,String>
     * RedisTemplate<Object,Object>
     * 框架创建的对象就叫 redisTemplate
     * @return
     */
    @PostMapping("/redis/addDate")
    public String addDate(){
        //操作String类型的数据，要先获得ValueOperations对象
        //使用redisTemplate
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //设置字符串
        valueOperations.set("name","周志雄");
        return "往redis添加了数据";
    }

    /**
     * 获取redis数据
     * @return
     */
    @GetMapping("/redis/getDate")
    public String getDate(){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String value=(String) valueOperations.get("name");
        return "获取的数据是:"+value;
    }

    @PostMapping("/redis/{key}/{value}")
    public String addStringDate(@PathVariable String key, @PathVariable String value){
        //操作String类型的数据，要先获得ValueOperations对象
        //使用stringRedisTemplate
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        //设置字符串
        valueOperations.set(key,value);
        return "===往redis添加了数据===";
    }

    @GetMapping("/redis/{key}")
    public String getStringDate(@PathVariable String key){
        ValueOperations valueOperations=stringRedisTemplate.opsForValue();
        String value=(String) valueOperations.get(key);
        return "===获取的数据是:"+value+"===";
    }

    @PostMapping("/redis/addStr")
    public String addString(String key,String value){
        //使用RedisTemplate
        //设置key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.opsForValue().set(key,value);
        return "自定义序列化";
    }

    @PostMapping("/redis/addJson")
    public String addJson(){
        Student student=new Student();
        student.setId(1001);
        student.setName("周志雄");
        student.setAge(20);
        //设置key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置值的序列化方式【表示要把Student对象作为json序列化]
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Student.class));
        //给redis数据库设置key和value的值
        Object object=redisTemplate.opsForValue().get("myStudent");
        return "json序列化的数据是:"+object;
    }
}

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 使用 java代码 完成 数据存取
 *
 *      不使用 redisTemplate  和 StringRedisTemplate
 *
 *
 */
public class RedisTest {


    //存取基本数据类型String
    @Test
    public  void test1(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.select(2);
        jedis.set("username","张三");
        System.out.println(jedis.get("username"));
        jedis.close();
    }

    @Test
    //存储过期字符串 (到了一定时间 自动过期)
    //后台处理 验证码失效的 操作
    public  void test2(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.select(2);
        jedis.setex("code",10,"4409");
        System.out.println(jedis.get("code"));
        jedis.close();
    }
    @Test
    //set
    public  void test3(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.select(2);

         jedis.sadd("myset","aa","bb","cc","aa");
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset.toString());
        jedis.close();
    }
    @Test
    //map
    public  void test4(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.select(2);

        jedis.hset("mymap","aa","11");
        jedis.hset("mymap","cc","22");

        Map<String, String> mymap = jedis.hgetAll("mymap");
        Set<String> set = mymap.keySet();
        for(String s:set){
            System.out.println(s+"---"+ mymap.get(s));
        }
        jedis.close();
    }
    @Test
    //list
    public  void test5(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.select(2);

        jedis.lpush("mylist","aa","bb","cc");
        jedis.rpush("mylist","aa","bb","cc");

        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist.toString());
        jedis.close();
    }

    @Test
    //sortset
    public  void test6(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.select(2);

        jedis.zadd("mysort",90,"tom");
        jedis.zadd("mysort",100,"jack");
        jedis.zadd("mysort",70,"lucy");

        Set<String> set = jedis.zrange("mysort", 0, -1);
        System.out.println(set.toString());
        jedis.close();
    }

}





















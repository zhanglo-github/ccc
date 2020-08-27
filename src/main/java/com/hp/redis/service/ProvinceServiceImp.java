package com.hp.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.redis.bean.Province;
import com.hp.redis.mapper.ProvinceMapper;
import com.hp.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.PrivateKey;
import java.util.List;

@Service
public class ProvinceServiceImp implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    //@Autowired
    //private RedisTemplate  redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String findProvinces() {
        //1. 从 redis 中,先获取一下缓存
        //String json = (String) redisTemplate.opsForValue().get("province");
        String json = (String)redisUtil.get("province");

        //2.  如果获取不到 连接数据库查询数据
        if(StringUtils.isEmpty(json)) {
            //2.1 查询后 转成 json  -- 然后存入redis
            List<Province> list =provinceMapper.findProcinces();
            ObjectMapper mapper = new ObjectMapper();
            try {
                json = mapper.writeValueAsString(list);    //将集合转成json字符串
                //然后存入redis
                //redisTemplate.opsForValue().set("province",json);
                redisUtil.set("province",json);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else{
            //使用的是缓存数据 ( 如果是新闻数据, 隔一定时间清除缓存)
            //2.2.如果获取到 不需做任何事
            System.out.println("使用缓存数据....");
        }

        //3.json返回
        return json;
    }
}

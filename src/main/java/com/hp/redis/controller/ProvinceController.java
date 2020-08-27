package com.hp.redis.controller;

import com.hp.redis.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pro")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    //拿到 省份的json数据
    @RequestMapping("/getJson")
    @ResponseBody
    public String getProvince(){

        System.out.println("张三修改了功能...........");
        System.out.println("张三修改了功能.fdasffsadfafsdafdsa..........");
        System.out.println("////////李四修改了自己模块/////////");
        //1.调用业务层 --- 拿到 json
        String json = provinceService.findProvinces();    //拿到所有 省 的数据
        //2. 返回
        return  json;
    }
}

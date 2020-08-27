package com.hp.redis.bean;

public class Province {

    //张三在 自己负责的模块 , 新增了功能
    private String desc;


    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

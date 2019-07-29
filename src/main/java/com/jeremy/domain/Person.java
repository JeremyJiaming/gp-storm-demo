package com.jeremy.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * storm传输的实体类
 */
@Data
public class Person {
    String name;    //姓名
    int age;     //年龄
    String job;     //工作

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    Date birthday;  //出生日期

    public Person(String name, int age, String job, Date birthday) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.birthday = birthday;
    }
}

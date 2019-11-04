package com.xiaoliebian.mybatis_plus;

import com.xiaoliebian.mybatis_plus.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mybatisAR {


    @Test
    public void artest01(){

        User user = new User();
        user.setName("皮舟");
        user.setAge(21);
        user.setEmail("pz@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());

        boolean insert = user.insert();
        System.out.println(insert);

    }








}

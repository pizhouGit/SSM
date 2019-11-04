package com.xiaoliebian.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
import com.xiaoliebian.mybatis_plus.Entity.User;
import com.xiaoliebian.mybatis_plus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mybayis_test {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){

        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }


    }


    @Test
    public void selectByWrapper(){

        //名字中包含雨并且年龄小于40
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
       // QueryWrapper<Object> query = Wrappers.query();

        queryWrapper.like("name","雨").lt("age",40);
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    public void selectByWrapper2(){

        //名字中包含雨并且年龄大于等于20且小于等于40并且email不为空
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        // QueryWrapper<Object> query = Wrappers.query();

        queryWrapper.like("name","雨").between("age",20,40).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }

    }


    @Test
    public void selectByWrapper3(){

        //名字为王或者年龄大于等于25，按照年龄降序排列，年龄相同的按照id升序排列
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        // QueryWrapper<Object> query = Wrappers.query();

       queryWrapper.likeRight("name","王")
               .or()
               .ge("age",25)
               .orderByDesc("age")
               .orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }

    }


    @Test
    public void selectByWrapper4(){

        //创建日前为2019年2月14日并且直属上级为名字是王姓
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        // QueryWrapper<Object> query = Wrappers.query();

        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
                .inSql("manager_id","select id from user where name like '王%'");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    public void selectByWrapper5(){

        //名字为王姓并且年龄小于40邮箱不为空
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        // QueryWrapper<Object> query = Wrappers.query();

      queryWrapper.likeRight("name","王")
              .lt("age",40).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    public void selectByWrapper6(){

        //年龄小于40或者邮箱不为空，并且名字为王姓
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        // QueryWrapper<Object> query = Wrappers.query();

        queryWrapper.lt("age",40)
                .isNotNull("email")
                .likeRight("name","王");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    public void selectByWrapper7(){

        //名字中包含雨并且年龄小于40---加强版
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        // QueryWrapper<Object> query = Wrappers.query();

       // queryWrapper.like("name","雨").lt("age",40);
        queryWrapper.select("id","name").like("name","雨").lt("age",40);
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    public void selectAllEq(){

        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();

        Map<String,Object> map=new HashMap<>();
        map.put("name","王天风");
        map.put("age",25);
        queryWrapper.allEq(map);

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void selectMrapperMaps(){

        //名字中包含雨并且年龄小于40---加强版
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();

        queryWrapper.select("id","name").like("name","雨").lt("age",40);
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }



    }

    @Test
    public void selectLambda(){
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();

        lambdaQuery.like(User::getName,"雨").lt(User::getAge,40);
        List<User> users = userMapper.selectList(lambdaQuery);
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void updateTest01(){
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(26);
        user.setEmail("wtf2@baomidou.com");
        int rows= userMapper.updateById(user);
        System.out.println("影响记录数："+rows);
    }


    @Test
    public void updateTest02(){
        UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<User>();

        userUpdateWrapper.eq("name","李艺伟").eq("age",28);

        User user = new User();
       user.setEmail("lyw2@baomidou.com");
        user.setAge(28);

        int update = userMapper.update(user, userUpdateWrapper);
        System.out.println("影响记录数"+update);


    }








}

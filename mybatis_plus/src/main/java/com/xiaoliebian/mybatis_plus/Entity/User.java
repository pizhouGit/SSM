package com.xiaoliebian.mybatis_plus.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User>{

    private static final long serialVersionUID=1L;

    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    //姓名
    @TableField(value = "name")
    private String name;

    //年龄
    @TableField(value = "age")
    private Integer age;

    //邮箱
    @TableField(value = "email")
    private String email;

    //直属上级
    @TableField(value = "manager_id")
    private Long managerId ;

    //创建时间
    @TableField(value = "create_time")
    private LocalDateTime createTime;


}

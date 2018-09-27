package com.MyBatis.dao;

import com.MyBatis.Entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserAnnotation {

    @Select("select * from t_user where id=#{id} ")
    public User getUser(Integer id);
}

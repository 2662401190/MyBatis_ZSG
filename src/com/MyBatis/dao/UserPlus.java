package com.MyBatis.dao;

import com.MyBatis.Entity.User;

import java.util.List;

/**
 * @author 贺威
 * @create 2018-09-27 16:31
 */
public interface UserPlus {

    /**
     * 根据id查询
     * @param id
     */
    User UserID(Integer id);

    User getUserAndAddress(Integer id );

    List<User> lists(Integer id);
}

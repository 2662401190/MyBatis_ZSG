package com.MyBatis.dao;

import com.MyBatis.Entity.User;

/**
 * @author 贺威
 * @create 2018-09-23 17:42
 */
public interface UserDao {

    /**
     *   根据id查询
     */
    User getUserID(Integer id);

    /***
     * 增加
     * @param user
     */
    void  addUser(User  user);

    /**
     * 修改
     * @param user
     */
     void  update(User user);

    /**
     * 删除
     */
     void  delete( Integer id);

}

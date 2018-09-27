package com.MyBatis.dao;

import com.MyBatis.Entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

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

    /**
     * 多条件查询
     */
    User cxs(@Param("sd") Integer id,@Param("qw") String name);

    /**
     * 传入一个map
     */
    User getmap(Map<String,Object> map);


    /**
     * 返回一个Map
     * @param id
     * @return
     */
    Map returnMap(Integer id);


    /**
     *
     * @param name
     * @return
     * @MapKey 注解告诉 MyBatis 封装map使用哪个属性做key
     */
    @MapKey("id")
    Map<Integer,User> getMapParam(String name);
}

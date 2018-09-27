package com.MyBatis.Entity;

import org.apache.ibatis.type.Alias;

/**
 * @author 贺威
 * @create 2018-09-23 12:56
 */
@Alias("sd")//别名
public class User {

    private  int id;
    private String sname;
    private String  sex;
    private Integer age;

    public User() {
        System.out.println("无参构造");
    }

    public User(int id, String name, String sex, Integer age) {
        this.id = id;
        this.sname = name;
        this.sex = sex;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return sname;
    }

    public void setName(String name) {
        this.sname = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
